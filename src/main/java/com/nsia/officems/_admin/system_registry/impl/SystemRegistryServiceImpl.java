package com.nsia.officems._admin.system_registry.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Query;

import com.nsia.officems._admin.system_registry.SystemRegistry;
import com.nsia.officems._admin.system_registry.SystemRegistryRepository;
import com.nsia.officems._admin.system_registry.SystemRegistryService;
import com.nsia.officems._identity.authentication.user.UserService;

@Service
public class SystemRegistryServiceImpl implements SystemRegistryService {

    @Autowired
    private SystemRegistryRepository systemRegistryRepository;

    @Autowired
    private UserService userService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SystemRegistry create(SystemRegistry systemRegistry) {
        return systemRegistryRepository.save(systemRegistry);
    }

    @Override
    public SystemRegistry delete(Long id) {
        SystemRegistry systemRegistry = findById(id);
        if (systemRegistry != null) {
            systemRegistryRepository.delete(systemRegistry);
        }
        return systemRegistry;
    }

    @Override
    public List findAll() {
        return systemRegistryRepository.findAll();
    }

    @Override
    public List<SystemRegistry> findAllByEnv(String envSlug) {
        return systemRegistryRepository.findByEnvSlug(envSlug);
    }

    @Override
    public SystemRegistry findById(Long id) {
        Optional<SystemRegistry> optionalObj = systemRegistryRepository.findById(id);
        return optionalObj.get();
    }

    @Override
    public List<SystemRegistry> findByName(String sysRegName) {
        return systemRegistryRepository.findByName(sysRegName);
    }

    @Override
    public boolean update(Long id, SystemRegistry systemRegistry) {
        if (id != null) {
            systemRegistry.setId(id);
            systemRegistry.setCreatedAt(findById(id).getCreatedAt());
        }
        // systemRegistry.setEnvSlug(userService.getCurrentEnv());
        systemRegistryRepository.save(systemRegistry);
        return true;
    }

    @Override
    public String getContentByRegistrySlug(String slug) {
        // TODO Auto-generated method stub
        List<SystemRegistry> systemRegistry = systemRegistryRepository.findByRegistrySlug(slug);

        if (systemRegistry.size() > 0) {
            return systemRegistry.get(0).getContent();
        }
        return null;
    }
}