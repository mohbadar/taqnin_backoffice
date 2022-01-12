package com.nsia.officems._identity.authentication.user;

import java.util.List;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    public User create(String userJson) throws Exception;

    public User delete(Long id);

    public List findAll();

    public User findById(Long id);

    public User findByUsername(String username);

    public boolean update(Long id, String userJson) throws JSONException;

    public void updateAvatar(User user, String avatarFilename);

    public User getLoggedInUser();

    public User getLoggedInUser(Boolean forceFresh);

    public String getSecurityContextHolderUsername(Boolean forceFresh);

    public boolean isAdmin();

    public User updatePreferences(String preferences);

    public boolean updateUserPassword(String currentPassword, String newPassword);

    public User findByEmail(String email);

    public void createPasswordResetTokenForUser(User user, String token, boolean active);

    public boolean validatePasswordResetToken(String token);

    public boolean createNewPassword(String newPassword, String confirmPassword, String token);

    public User uploadImage(long id, MultipartFile file);

    public Long getWorkFlowId();
    public Long getDepartmentId();

}
