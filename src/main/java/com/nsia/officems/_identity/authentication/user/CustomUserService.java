package com.nsia.officems._identity.authentication.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CustomUserService extends UserDetailsService {

	// User findByEmail(String email);

	public CustomUser loadUserByUsername(String username);

	public CustomUser loadUserByUsername(String username, String currentLang);
}
