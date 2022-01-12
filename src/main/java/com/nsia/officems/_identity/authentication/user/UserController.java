package com.nsia.officems._identity.authentication.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
// import com.nsia.config.aspect.Loggable;
import com.nsia.officems._identity.authentication.group.Group;
import com.nsia.officems._identity.authentication.group.GroupService;
// import com.nsia.jobs.Job;
import com.nsia.officems._util.ExceptionUtil;
import com.nsia.officems._util.JsonParserUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping({ "/api/users" })

public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${spring.mail.to}")
    private String emailTo;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private GroupService groupService;
    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private ExceptionUtil exceptionUtil;

    @Autowired
    private JsonParserUtil jsonParserUtil;

    // @Autowired
    // private SessionRegistry sessionRegistry;

    private String failureEmailSub = "500 - ASIMS Exception - User";

    ObjectMapper mapper = new ObjectMapper();

    public boolean hasAuthority(SimpleGrantedAuthority auth) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Collection<? extends GrantedAuthority> auths = ((UserDetails) principal).getAuthorities();
        if (auths.contains(auth)) {
            return true;
        }
        return false;
    }

    @GetMapping
    public List<User> findAll() {
        // String envSlug = userAuthService.getCurrentEnv();
        return userAuthService.findAll();
    }

    @GetMapping("/one")
    public ResponseEntity<User> findUserByUsername(@RequestParam("username") String username) {
        User user = userAuthService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ObjectNode detail(@PathVariable("id") Long id) {
        User user = userAuthService.findById(id);
        CustomUser customUser = customUserService.loadUserByUsername(user.getUsername());
        List<Group> groupList = groupService.findAll();
        /**
         * Return all the groups
         */
        // List<Object> groupList = groupService.findAllByEnvs(userEnvSlugs);
        ArrayNode allGroups = mapper.valueToTree(groupList);
        JsonNode userJson = mapper.convertValue(user, JsonNode.class);
        JsonNode groupJson = mapper.convertValue(user.getGroups(), JsonNode.class);
        // JsonNode jobsJson = mapper.convertValue(user.getJob(), JsonNode.class);
        JsonNode authoritiesJson = mapper.convertValue(customUser.getAuthorities(), JsonNode.class);
        ObjectNode userObj = mapper.createObjectNode();
        // List<Job> jobsList =
        // this.userAuthService.findAllJobsByEnvSlug(userAuthService.getCurrentEnv());
        // List<Job> jobsList = this.daoUtil.findAllJobs(
        // userAuthService.getCurrentEnv());
        // ArrayNode jobsNode = mapper.valueToTree(jobsList);
        userObj.set("user", userJson);
        // userObj.set("job", jobsJson);
        // userObj.set("allJobs", jobsNode);
        userObj.set("groups", groupJson);
        userObj.set("authorities", authoritiesJson);
        userObj.set("allGroups", allGroups);
        if (hasAuthority(new SimpleGrantedAuthority("SYS_ADMIN"))) {
        } else {
            userObj.set("environments", mapper.valueToTree(new ArrayList()));
        }
        return userObj;
    }

    @PostMapping
    public User create(@RequestBody String userPayload, HttpServletRequest request) throws Exception {
        logger.info("Entry UserController>CREATE() - POST");
        JSONObject userObject = jsonParserUtil.parse(userPayload);

        User userNameExists = userAuthService.findByUsername(userObject.getString("username"));
        if (userNameExists != null)
            throw new Exception("Username already exists");

        try {
            return userAuthService.create(userPayload);
        } catch (Exception e) {
            User currentLoggedInUser = userAuthService.getLoggedInUser();
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, null, request, false);
            return null;
        }
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable("id") Long id, @RequestBody String userPayload, HttpServletRequest request)
            throws Exception {
        logger.info("Entry UserController>update() - PUT");
        // Gson gson=new Gson();
        // User newUser=gson.fromJson(user, User.class);
        try {
            return userAuthService.update(id, userPayload);
        } catch (Exception e) {
            User currentLoggedInUser = userAuthService.getLoggedInUser();
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, null, request, false);
            return false;
        }
    }

    @GetMapping(value = "/{id}/groups", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Group> getLoggedInUserGroups(@PathVariable(value = "id", required = true) Long loggedInUserId) {
        return userAuthService.findById(loggedInUserId).getGroups();
    }

    @PutMapping(value = "/preferences")
    public User updatePreferences(@RequestBody String preferences, HttpServletRequest request) throws Exception {
        logger.info("Entry UserController>updatePreferences() - PUT");
        try {
            return userAuthService.updatePreferences(preferences);
        } catch (Exception e) {
            User currentLoggedInUser = userAuthService.getLoggedInUser();
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, null, request, false);
            return null;
        }
    }

    @PutMapping(value = "/cpassword")
    public boolean updateUserPassword(@RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword, HttpServletRequest request) throws Exception {
        logger.info("Entry UserController>updateUserPassword() - PUT");

        try {
            return userAuthService.updateUserPassword(currentPassword, newPassword);
        } catch (Exception e) {
            User currentLoggedInUser = userAuthService.getLoggedInUser();
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, null, request, false);
            return false;
        }
    }

    /**
     * fetch necessary info to create a new user it includes groups, jobs
     * 
     * @Author HPardess
     * @returns ObjectNode of the response object
     */

    @GetMapping(value = "/creation-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode getCreationRequiredData() {
        List<Group> groupList = groupService.findAll();
        // List<Job> jobsList =
        // userAuthService.findAllJobsByEnvSlug(userAuthService.getCurrentEnv());
        ArrayNode allGroups = mapper.valueToTree(groupList);
        // ArrayNode jobsNode = mapper.valueToTree(jobsList);
        ObjectNode dataNode = mapper.createObjectNode();
        dataNode.set("groups", allGroups);
        // dataNode.set("jobs", jobsNode);
        return dataNode;
    }

    /*
     * Return all logged-in users
     * 
     * @Author HPardess
     * 
     * @returns a list of all currently logged-in users
     * 
     * 
     */
    @GetMapping(value = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllLoggedInUsers() {
        // final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        // List<User> allLoggedInUser = new ArrayList();
        // for(final Object principal : allPrincipals) {
        // if(principal instanceof User) {
        // System.out.println("the user >>>>>"+((User)principal).toString());
        // allLoggedInUser.add((User) principal);
        // }
        // }
        // return allLoggedInUser;
        return null;
    }

    @PostMapping(value = "/upload-image")
    public Boolean uploadImage(@RequestParam(name = "avatar", required = false) MultipartFile file,
            @RequestParam("id") long id, HttpServletRequest request) throws Exception {
        System.out.println("******************** Image upload **********");
        User user = userAuthService.uploadUserImage(id, file);
        if (!user.equals(null)) {
            return true;
        }

        return null;
    }

}
