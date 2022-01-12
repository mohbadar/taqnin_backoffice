package com.nsia.officems._identity.authentication.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nsia.officems._identity.config.JwtTokenUtil;
import com.nsia.officems._util.EmailUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonParseException;
import com.nsia.officems._identity.authentication.user.CustomUser;
import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.extern.slf4j.Slf4j;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.sasl.AuthenticationException;

@RestController
@RequestMapping({ "/api" })
@Slf4j
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${app.url}")
    private String appUrl;

    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager em;

    ObjectMapper mapper = new ObjectMapper();

    @Value("${app.user.avatar}")
    private String uploadAvatarDir;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody Map<String, String> loginUser) throws AuthenticationException {
        final String username = loginUser.get("username").toLowerCase();
        final String password = loginUser.get("password");
        final String currentLang = loginUser.get("lang");

        // Session session = em.unwrap(Session.class);
        // session.enableFilter("taxCentreFilter").setParameter("taxCentreNo", 19L);

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        String encodedPassword = this.passwordEncoder.encode(password);
        System.out.println("the password is<<<<<<<<<<<<<<<<:" + encodedPassword);

        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final CustomUser customUser = customUserService.loadUserByUsername(username);
        customUser.setCurrentLang(currentLang);
        System.out.println("Lang: " + customUser.getCurrentLang());

        Authentication newAuth = new UsernamePasswordAuthenticationToken(customUser, authentication.getCredentials(),
                customUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        final String token = jwtTokenUtil.generateToken(customUser);

        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity logout(HttpServletRequest request) {
        // TODO delete the token

        return ResponseEntity.ok(true);
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
    public boolean forgotpassword(@RequestBody String email, HttpServletRequest request) throws Exception {
        User user = userService.findByEmail(email);
        if (user == null) {
            return false;
        }

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token, true);

        String msg = "Dear Mr." + user.getName() + ",<br /><br />"
                + "We have received a request to reset your password, if you did not make this request just ignore this email. Otherwise , you can reset your password using this link <br /><br />Note: This link can be use for one time and validated for 30 minutes only.<br /><br />"
                + appUrl + "changepassword?token=" + token;
        emailUtil.sendMessage(email, "Password Reset", msg);

        return true;
    }

    @RequestMapping(value = "/changepassword", method = RequestMethod.PUT)
    public boolean changePassword(@RequestParam String newPassword, @RequestParam String confirmPassword,
            @RequestParam String token) {

        boolean result = userService.validatePasswordResetToken(token);
        if (result == true) {
            return userService.createNewPassword(newPassword, confirmPassword, token);
        }
        return false;
    }

    @PutMapping(path = "/config")
    public ObjectNode updateConfig(Authentication authentication, @RequestParam("lang") String lang,
            @RequestParam("env") String envSlug) throws JsonParseException, IOException {
        String currentEnv = envSlug;
        String currentLang = lang;
        System.out.println("current environment:" + currentEnv + "current lang" + currentLang);
        CustomUser userDetails = (CustomUser) authentication.getPrincipal();
        userDetails.setCurrentLang(currentLang);
        System.out.println("inside user details " + "--" + userDetails.getCurrentLang());
        userDetails = customUserService.loadUserByUsername(userDetails.getUsername(), currentLang);

        Authentication newAuth = new UsernamePasswordAuthenticationToken(userDetails, authentication.getCredentials(),
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        // as CurrentEnv and CurrentLang are changed. so it is required to create new
        // token and share it with client
        final String newToken = jwtTokenUtil.generateToken(userDetails);

        User user = userService.findByUsername(userDetails.getUsername());

        ObjectNode result = getConfiguration(user, userDetails);
        result.put("token", newToken);
        return result;
    }

    @GetMapping(path = "/config")
    public ObjectNode config(Authentication authentication) throws JsonParseException, IOException {
        CustomUser userDetails = (CustomUser) authentication.getPrincipal();

        User user = userService.findByUsername(userDetails.getUsername());
        return getConfiguration(user, userDetails);
    }

    private ObjectNode getConfiguration(User user, CustomUser userDetails) throws JsonParseException, IOException {
        ObjectNode config = mapper.createObjectNode();

        // Language preferLang = irdEmployee.getPreferLanguage();

        // User Details
        config.put("id", user.getId());
        config.put("name", user.getName());
        config.put("username", userDetails.getUsername());
        config.put("authenticated", true);

        config.put("currentLang", userDetails.getCurrentLang());

        // if(preferLang != null) {
        // config.put("preferedLang", preferLang.getSlug());
        // }

        // user preferences
        String prefStr = user.getPreferences();
        JsonNode prefJson = null;
        if (prefStr != null && prefStr.equals("") == false) {
            prefJson = mapper.readTree(prefStr);
        }
        config.set("envPreferences", prefJson);

        // User Autorities
        ArrayNode authorities = mapper.createArrayNode();
        for (GrantedAuthority auth : userDetails.getAuthorities()) {
            authorities.add(auth.getAuthority());
        }
        config.set("authorities", authorities);

        // System.out.println("final config is:"+config.toString());
        return config;
    }

    @RequestMapping("/token")
    public Map<String, String> token(HttpSession session) {
        return Collections.singletonMap("token", session.getId());
    }

    @GetMapping("/profile")
    public User profile() {
        logger.debug("Getting loggedin user profile");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }

        return userService.findByUsername(username);
    }

    @PatchMapping("/avatar")
    public User updateAvatar(@RequestParam(value = "avatar", required = true) MultipartFile file) throws IOException {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        String fileName = formatter.format(Calendar.getInstance().getTime()) + "_thumbnail.jpg";
        User user = userService.getLoggedInUser();
        if (!file.isEmpty()) {
            try {
                String saveDirectory = uploadAvatarDir + File.separator + user.getId() + File.separator;
                File test = new File(saveDirectory);
                if (!test.exists()) {
                    test.mkdirs();
                }

                byte[] bytes = file.getBytes();

                ByteArrayInputStream imageInputStream = new ByteArrayInputStream(bytes);
                BufferedImage image = ImageIO.read(imageInputStream);
                BufferedImage thumbnail = Scalr.resize(image, 200);

                File thumbnailOut = new File(saveDirectory + fileName);
                ImageIO.write(thumbnail, "png", thumbnailOut);

                userService.updateAvatar(user, fileName);
                System.out.println("Image Saved::: " + fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userService.getLoggedInUser(true); // Force refresh of cached User
    }

    @RequestMapping(value = "/user/profile-picture", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] profilePicture() throws IOException {
        User user = userService.getLoggedInUser();
        String profilePicture = uploadAvatarDir + File.separator + user.getId() + File.separator + user.getAvatar();
        if (new File(profilePicture).exists()) {
            return IOUtils.toByteArray(new FileInputStream(profilePicture));
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/user/avatar", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] profileAvatar() throws IOException {
        User user = userService.getLoggedInUser();
        String profilePicture = uploadAvatarDir + File.separator + user.getId() + File.separator + user.getAvatar();
        if (new File(profilePicture).exists()) {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(profilePicture));
            BufferedImage thumbnail = Scalr.resize(bufferedImage, 128);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(thumbnail, "jpg", bos);

            return bos.toByteArray();
        } else {
            return null;
        }
    }
}
