package com.nsia.officems._identity.authentication.role.impl;

import java.util.List;

import com.nsia.officems._identity.authentication.role.Role;
import com.nsia.officems._identity.authentication.role.RoleRepository;
import com.nsia.officems._identity.authentication.role.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * class to do all the CRUD operations on a Role
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role create(Role role) {

        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public boolean update(Long id, Role role) {
        if (id != null) {
            role.setId(id);
        }

        roleRepository.save(role);
        return true;
    }

    @Override
    public List<Role> delete(Long id) {

        Role role = roleRepository.findById(id).get();
        if (role != null) {
            roleRepository.deleteById(id);
            roleRepository.flush();

        }
        return roleRepository.findAll();
    }

}
