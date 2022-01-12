package com.nsia.officems._admin.organization;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    public List<Organization> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
}
