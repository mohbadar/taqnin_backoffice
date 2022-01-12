package com.nsia.officems._identity.authentication.permission.impl;

import java.util.List;

import com.nsia.officems._identity.authentication.permission.Permission;
import com.nsia.officems._identity.authentication.permission.PermissionRepository;
import com.nsia.officems._identity.authentication.permission.PermissionService;
import com.nsia.officems._identity.authentication.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public List<Permission> findAll() {
		return permissionRepository.findAll();
	}

	@Override
	public List<Permission> findAllByUser(User user) {
		System.out.println("**************** USER ID IS " + user.getId() + "*****************");
		return permissionRepository.findAllByUser(user.getId());
	}
}
