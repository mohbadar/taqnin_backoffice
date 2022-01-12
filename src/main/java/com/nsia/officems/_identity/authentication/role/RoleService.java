package com.nsia.officems._identity.authentication.role;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface RoleService {

    public Role create(Role role);

    public List<Role> delete(Long id);

    public List<Role> findAll();

    public Role findById(Long id);

    public boolean update(Long id, Role role);
}
