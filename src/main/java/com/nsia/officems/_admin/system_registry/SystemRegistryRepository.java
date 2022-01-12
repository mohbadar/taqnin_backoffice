package com.nsia.officems._admin.system_registry;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemRegistryRepository extends JpaRepository<SystemRegistry, Long>  {
	public List<SystemRegistry> findByEnvSlug(String envSlug);
	public List<SystemRegistry> findByName(String name);
	public List<SystemRegistry> findByRegistrySlug(String slug);
}
