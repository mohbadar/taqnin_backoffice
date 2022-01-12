package com.nsia.officems._admin.organization.impl;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nsia.officems._admin.organization.Organization;
import com.nsia.officems._admin.organization.OrganizationRepository;
import com.nsia.officems._admin.organization.OrganizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public List<Organization> findAll() {
        return organizationRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public Organization findById(Long id) {
        Optional<Organization> optionalObj = organizationRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Organization create(Organization organization) {
        organization.setDeleted(false);
        return organizationRepository.save(organization);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Organization> organization = organizationRepository.findById(id);

        if (organization.isPresent()) {
            Organization org2 = organization.get();
            org2.setDeleted(true);
            // ethnic.setDeletedBy(userService.getLoggedInUser().getUsername());
            org2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            organizationRepository.save(org2);
            return true;
        }

        return false;
    }

    @Override
    public boolean update(Long id, Organization org) {
        Optional<Organization> orgToBeUpdated = organizationRepository.findById(id);
        if (orgToBeUpdated.isEmpty()) {
            return false;
        }

        Organization editedOrg = orgToBeUpdated.get();

        editedOrg.setNameEn(org.getNameEn());
        editedOrg.setNameDr(org.getNameDr());
        editedOrg.setNamePs(org.getNamePs());

        organizationRepository.save(editedOrg);
        return true;
    }

}
