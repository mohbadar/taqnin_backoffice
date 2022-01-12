package com.nsia.officems._admin.system_registry;

import java.util.List;

public interface SystemRegistryService {
	public SystemRegistry create(SystemRegistry systemRegistry);
    public SystemRegistry delete(Long id);
    public List<SystemRegistry> findAll();
    public List<SystemRegistry> findAllByEnv(String envSlug);
    public SystemRegistry findById(Long id);
    public List<SystemRegistry> findByName(String sysRegName);
    public boolean update(Long id, SystemRegistry systemRegistry);
    public String getContentByRegistrySlug(String slug);
}
