package com.nsia.officems._admin.authority;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.validation.Valid;

import com.nsia.officems._admin.authority.requests.CreateAuthorityRequest;
import com.nsia.officems._admin.authority.requests.EditAuthorityRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/authorities")
public class AuthorityController {
    private final ModelMapper _modelMapper;

    @Autowired
    private AuthorityService authorityService;

    @GetMapping()
    public List<Authority> findAll() {
        return authorityService.findAll();
    }

    @GetMapping(path = { "/{id}" })
    public Authority findById(@PathVariable("id") Long id) {
        return authorityService.findById(id);
    }

    @PostMapping()
    public Authority create(@RequestBody CreateAuthorityRequest createAuthorityRequest) {
        System.out.println("Create Controller");
        Authority newAuthority = _modelMapper.map(createAuthorityRequest, Authority.class);
        return authorityService.create(newAuthority);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(authorityService.delete(id));
    }

    @PutMapping(path = "/{id}")
    public boolean updateAuthorityById(@PathVariable(value = "id") Long id,
            @Valid @RequestBody EditAuthorityRequest editAuthorityRequest) {
        Authority updateAuthority = _modelMapper.map(editAuthorityRequest, Authority.class);
        return authorityService.update(id, updateAuthority);
    }

}
