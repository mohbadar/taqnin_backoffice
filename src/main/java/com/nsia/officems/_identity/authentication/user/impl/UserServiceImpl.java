package com.nsia.officems._identity.authentication.user.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;

import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._admin.organization.OrganizationService;
import com.nsia.officems._identity.authentication.auth.PasswordResetToken;
import com.nsia.officems._identity.authentication.auth.PasswordResetTokenRepository;
import com.nsia.officems._identity.authentication.group.Group;
import com.nsia.officems._identity.authentication.group.GroupService;
import com.nsia.officems._identity.authentication.user.CustomUser;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserRepository;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.EmailUtil;
import com.nsia.officems._util.JsonParserUtil;
import com.nsia.officems.taqnin.step.Step;
import com.nsia.officems.taqnin.step.StepService;
import com.nsia.officems.taqnin.workflow.Workflow;
import com.nsia.officems.taqnin.workflow.WorkflowService;

@Service
public class UserServiceImpl implements UserService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;
	@Autowired
	private EmailUtil emailUtil;

	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	@Autowired
	private GroupService groupService;

	@Autowired
	private JsonParserUtil jsonParserUtil;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private WorkflowService workflowService;

	@Value("${app.upload.user}")
	private String uploadDir;

	public String encodePassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	@Override
	public User create(String userJson) throws Exception {
		String defaultPassword = encodePassword("secret");
		User user = new User();

		try {
			if (jsonParserUtil.isValid(userJson)) {
				JSONObject jsonObj = jsonParserUtil.parse(userJson);

				if (jsonObj.has("name"))
					user.setName(jsonObj.isNull("name") ? "" : jsonObj.getString("name"));
				if (jsonObj.has("username"))
					user.setUsername(jsonObj.isNull("username") ? "" : jsonObj.getString("username").trim());
				if (jsonObj.has("phone_no"))
					user.setPhoneNo(jsonObj.isNull("phone_no") ? "" : jsonObj.getString("phone_no"));
				if (jsonObj.has("address"))
					user.setAddress(jsonObj.isNull("address") ? "" : jsonObj.getString("address"));
				if (jsonObj.has("email"))
					user.setEmail(jsonObj.isNull("email") ? "" : jsonObj.getString("email"));
				if (jsonObj.has("active"))
					user.setActive(jsonObj.isNull("active") ? false : jsonObj.getBoolean("active"));

				if (jsonObj.has("password")) {
					user.setPassword(jsonObj.isNull("password") ? "" : jsonObj.getString("password").trim());
				}

				if (jsonObj.has("confirm_password"))
					user.setConfirmPassword(
							jsonObj.isNull("confirm_password") ? "" : jsonObj.getString("confirm_password").trim());

				if (jsonObj.has("groups")) {
					if (!jsonObj.isNull("groups")) {
						Set<Group> groups = new HashSet<>();
						JSONArray groupsArray = jsonObj.getJSONArray("groups");

						for (int i = 0; i < groupsArray.length(); i++) {
							groups.add(groupService.findById(groupsArray.getLong(i)));
						}

						user.setGroups(groups);
					}
				}

				if (jsonObj.has("is_client")){
					user.setIsClient(jsonObj.isNull("is_client") ? false : jsonObj.getBoolean("is_client"));
				}

				if (jsonObj.has("is_workflow")) {
					user.setIsWorkflow(jsonObj.isNull("is_workflow") ? false : jsonObj.getBoolean("is_workflow"));
				}

				if (jsonObj.has("is_admin")) {
					user.setIsAdmin(jsonObj.isNull("is_admin") ? false : jsonObj.getBoolean("is_admin"));
				}

				if (jsonObj.has("entity_id")) {
					if (!jsonObj.isNull("entity_id")) {
						user.setEntity(organizationService.findById(jsonObj.getLong("entity_id")));
					}
				}

				if (jsonObj.has("workflow_id")) {
					if (!jsonObj.isNull("workflow_id")) {
						user.setWorkflow(workflowService.findById(jsonObj.getLong("workflow_id")));
					}
				}

				if (jsonObj.has("departmentId")) {
					if (!jsonObj.isNull("departmentId")) {
						user.setDepartment(departmentService.findById(jsonObj.getLong("departmentId")));
					}
				}


				

				if (user.isMatchingPasswords()) {
					String usersEncodedPassword = encodePassword(user.getPassword().trim());
					if (usersEncodedPassword == null) {
						user.setPassword(defaultPassword);
					} else {
						user.setPassword(usersEncodedPassword);
					}

					user = userRepository.save(user);
					return user;
				}
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return null;
	}

	@Override
	public User delete(Long id) {
		User project = findById(id);
		if (project != null) {
			userRepository.delete(project);
		}
		return project;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(Long id) {
		Optional<User> optionalObj = userRepository.findById(id);
		User user = optionalObj.get();
		return user;
	}

	@Override
	public User findByUsername(String username) {
		try {
			User user = userRepository.findByUsername(username);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(Long id, String userJson) throws JSONException {
		System.out.println("UserServiceImp > update : " + id);

		if (id != null) {
			User user = findById(id);

			if (jsonParserUtil.isValid(userJson)) {
				JSONObject jsonObj = jsonParserUtil.parse(userJson);
				if (jsonObj.has("name"))
					user.setName(jsonObj.isNull("name") ? "" : jsonObj.getString("name"));
				if (jsonObj.has("username"))
					user.setUsername(jsonObj.isNull("username") ? "" : jsonObj.getString("username").trim());
				if (jsonObj.has("phone_no"))
					user.setPhoneNo(jsonObj.isNull("phone_no") ? "" : jsonObj.getString("phone_no"));
				if (jsonObj.has("address"))
					user.setAddress(jsonObj.isNull("address") ? "" : jsonObj.getString("address"));
				if (jsonObj.has("email"))
					user.setEmail(jsonObj.isNull("email") ? "" : jsonObj.getString("email"));
				if (jsonObj.has("active"))
					user.setActive(jsonObj.isNull("active") ? false : jsonObj.getBoolean("active"));

				if (jsonObj.has("groups")) {
					if (!jsonObj.isNull("groups")) {
						Set<Group> groups = new HashSet<>();

						JSONArray groupsArray = jsonObj.getJSONArray("groups");
						for (int i = 0; i < groupsArray.length(); i++) {
							groups.add(groupService.findById(groupsArray.getLong(i)));
						}

						user.setGroups(groups);
					}
				}

				if (jsonObj.has("is_client")){
					user.setIsClient(jsonObj.isNull("is_client") ? false : jsonObj.getBoolean("is_client"));
				}

				if (jsonObj.has("is_workflow")) {
					user.setIsWorkflow(jsonObj.isNull("is_workflow") ? false : jsonObj.getBoolean("is_workflow"));
				}

				if (jsonObj.has("is_admin")) {
					user.setIsAdmin(jsonObj.isNull("is_admin") ? false : jsonObj.getBoolean("is_admin"));
				}

				if (jsonObj.has("entity_id")) {
					if (!jsonObj.isNull("entity_id")) {
						user.setEntity(organizationService.findById(jsonObj.getLong("entity_id")));
					}
				}

				if (jsonObj.has("workflow_id")) {
					if (!jsonObj.isNull("workflow_id")) {
						user.setWorkflow(workflowService.findById(jsonObj.getLong("workflow_id")));
					}
				}

				if (jsonObj.has("departmentId")) {
					if (!jsonObj.isNull("departmentId")) {
						user.setDepartment(departmentService.findById(jsonObj.getLong("departmentId")));
					}
				}

			}

			userRepository.save(user);
			return true;
		}
		return false;
	}

	@Override
	public void updateAvatar(User user, String avatarFilename) {
		userRepository.updateAvatar(user.getUsername(), avatarFilename);
	}

	@Override
	public User getLoggedInUser() {
		return getLoggedInUser(false);
	}

	@Override
	public User getLoggedInUser(Boolean forceFresh) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = principal.toString();

		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}

		return findByUsername(username);
	}

	@Override
	public String getSecurityContextHolderUsername(Boolean forceFresh) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = principal.toString();

		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}

		return username;
	}

	@Override
	public boolean isAdmin() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<? extends GrantedAuthority> auths = ((UserDetails) principal).getAuthorities();
		if (auths.contains(new SimpleGrantedAuthority("ADMIN"))) {
			return true;
		}

		return false;

	}

	@Override
	public User updatePreferences(String preferences) {
		// update the preferenes of currently logged-in user
		User user = getLoggedInUser();
		user.setPreferences(preferences);
		System.out.println("user updated:" + user.toString());
		return userRepository.save(user);

	}

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public boolean updateUserPassword(String currentPassword, String newpassword) {
		User loggedInUser = getLoggedInUser();

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String newCurrentPassword = bCryptPasswordEncoder.encode(newpassword);

		if (passwordEncoder.matches(currentPassword, loggedInUser.getPassword())) {
			loggedInUser.setPassword(newCurrentPassword);
			userRepository.save(loggedInUser);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Long getWorkFlowId(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUser userDetials = (CustomUser) auth.getPrincipal();
		return userDetials.getWorkFlowId();
	}

	@Override
	public Long getDepartmentId(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUser userDetials = (CustomUser) auth.getPrincipal();
		return userDetials.getDepartmentId();
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token, boolean active) {
		PasswordResetToken myToken = new PasswordResetToken(token, user, active);
		passwordResetTokenRepository.save(myToken);
	}

	@Override
	public boolean validatePasswordResetToken(String token) {
		PasswordResetToken passToken = passwordResetTokenRepository.findByToken(token);
		if (passToken == null || passToken.getActive() == false) {
			return false;
		}

		LocalDateTime time = LocalDateTime.now();
		if (passToken.getExpiryDate().plusMinutes(30).isBefore(time)) {
			passToken.setActive(false);
			passwordResetTokenRepository.save(passToken);
			return false;
		}

		return true;
	}

	@Override
	public boolean createNewPassword(String newPassword, String confirmPassword, String token) {
		if (newPassword.equals(confirmPassword)) {
			PasswordResetToken userToken = passwordResetTokenRepository.findByToken(token);
			Long userId = userToken.getUser().getId();
			String newPass = encodePassword(newPassword);
			User user = userService.findById(userId);
			user.setPassword(newPass);
			userRepository.save(user);
			userToken.setActive(false);
			passwordResetTokenRepository.save(userToken);
			return true;
		}
		return false;
	}
	// Collection<? extends GrantedAuthority> getAuths

	public User uploadImage(long id, MultipartFile file) {
		User user = userService.findById(id);

		if (!user.equals(null)) {
			String fileName = saveAttachment(file, user.getId());
			user.setAvatar(fileName);
			return userRepository.save(user);
		}
		return null;
	}

	private String saveAttachment(MultipartFile file, Long id) {
		System.out.println("_________________________DATA: ");
		Format formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
		String fileName;
		// User user = userAuthService.getLoggedInUser();
		if (file != null) {
			System.out.println("INSIDE --------------- file: ");
			fileName = formatter.format(Calendar.getInstance().getTime()) + "_user_" + file.getOriginalFilename();
			try {
				String saveDirectory = uploadDir + File.separator + File.separator + id + File.separator + "image"
						+ File.separator;
				File test = new File(saveDirectory);
				if (!test.exists()) {
					test.mkdirs();
				}

				byte[] bytes = file.getBytes();

				ByteArrayInputStream imageInputStream = new ByteArrayInputStream(bytes);
				BufferedImage image = ImageIO.read(imageInputStream);
				BufferedImage thumbnail = Scalr.resize(image, 200);

				File thumbnailOut = new File(saveDirectory + fileName);
				ImageIO.write(image, "png", thumbnailOut);

				System.out.println("Image Saved::: " + fileName);
				return fileName;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

}
