package com.nsia.officems._admin.organization;

import java.util.List;

import javax.validation.Valid;

import com.nsia.officems._admin.organization.requests.CreateOrganizationRequest;
import com.nsia.officems._admin.organization.requests.EditOrganizationRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/admin/organizations")
public class OrganizationController {

    private final ModelMapper _modelMapper;

    @Autowired
    private OrganizationService organizationService;

    @PreAuthorize("hasAuthority('ORGANIZATION_LIST')")
    @GetMapping()
    public List<Organization> findAll() {
        return organizationService.findAll();
    }

    @PreAuthorize("hasAuthority('ORGANIZATION_VIEW')")
    @GetMapping(path = { "/{id}" })
    public Organization findById(@PathVariable("id") Long id) {
        return organizationService.findById(id);
    }

    @PreAuthorize("hasAuthority('ORGANIZATION_CREATE')")
    @PostMapping()
    public Organization create(@RequestBody CreateOrganizationRequest createOrganizationRequest) {
        System.out.println("Create Controller");
        Organization newOrg = _modelMapper.map(createOrganizationRequest, Organization.class);
        return organizationService.create(newOrg);
    }

    @PreAuthorize("hasAuthority('ORGANIZATION_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(organizationService.delete(id));
    }

    @PreAuthorize("hasAuthority('ORGANIZATION_EDIT')")
    @PutMapping(path = "/{id}")
    public boolean updateMinistryById(@PathVariable(value = "id") Long id,
            @Valid @RequestBody EditOrganizationRequest editOrganizationRequest) {

        Organization updateOrganization = _modelMapper.map(editOrganizationRequest, Organization.class);
        return organizationService.update(id, updateOrganization);
    }

}
