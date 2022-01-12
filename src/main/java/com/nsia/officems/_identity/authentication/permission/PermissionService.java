package com.nsia.officems._identity.authentication.permission;

import java.util.List;

import com.nsia.officems._identity.authentication.user.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface PermissionService {
	public List findAll();

	public List<Permission> findAllByUser(User user);
}
