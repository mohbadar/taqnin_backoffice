package com.nsia.officems._admin.organization;

import java.util.List;

public interface OrganizationService {
    public List<Organization> findAll();

    public Organization findById(Long id);

    public Organization create(Organization organization);

    public Boolean delete(Long id);

    public boolean update(Long id, Organization organization);

}
