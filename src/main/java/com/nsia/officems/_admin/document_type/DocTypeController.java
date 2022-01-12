package com.nsia.officems._admin.document_type;

import java.util.List;

import javax.validation.Valid;

import com.nsia.officems._admin.document_type.requests.CreateDocTypeRequest;
import com.nsia.officems._admin.document_type.requests.EditDocTypeRequest;

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
@RequestMapping(value = "/api/admin/doctypes")
public class DocTypeController {

    private final ModelMapper _modelMapper;

    @Autowired
    private DocTypeService doctypeService;

    @PreAuthorize("hasAuthority('DOCTYPE_LIST')")
    @GetMapping()
    public List<DocType> findAll() {
        return doctypeService.findAll();
    }

    @PreAuthorize("hasAuthority('DOCTYPE_VIEW')")
    @GetMapping(path = { "/{id}" })
    public DocType findById(@PathVariable("id") Long id) {
        return doctypeService.findById(id);
    }

    @PreAuthorize("hasAuthority('DOCTYPE_CREATE')")
    @PostMapping()
    public DocType create(@RequestBody CreateDocTypeRequest createDocTypeRequest) {
        System.out.println("Create Controller");
        DocType newOrg = _modelMapper.map(createDocTypeRequest, DocType.class);
        return doctypeService.create(newOrg);
    }

    @PreAuthorize("hasAuthority('DOCTYPE_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(doctypeService.delete(id));
    }

    @PreAuthorize("hasAuthority('DOCTYPE_EDIT')")
    @PutMapping(path = "/{id}")
    public boolean updateMinistryById(@PathVariable(value = "id") Long id,
            @Valid @RequestBody EditDocTypeRequest editDocTypeRequest) {

        DocType updateDocType = _modelMapper.map(editDocTypeRequest, DocType.class);
        return doctypeService.update(id, updateDocType);
    }

}
