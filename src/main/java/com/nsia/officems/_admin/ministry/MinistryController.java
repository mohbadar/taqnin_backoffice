package com.nsia.officems._admin.ministry;

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

import com.nsia.officems._admin.ministry.requests.CreateMinistryRequest;
import com.nsia.officems._admin.ministry.requests.EditMinistryRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/admin/ministries")
public class MinistryController {

    private final ModelMapper _modelMapper;

    @Autowired
    private MinistryService ministryService;

    @GetMapping()
    public List<Ministry> findAll() {
        return ministryService.findAll();
    }

    @GetMapping(path = { "/{id}" })
    public Ministry findById(@PathVariable("id") Long id) {
        return ministryService.findById(id);
    }

    @PostMapping()
    public Ministry create(@RequestBody CreateMinistryRequest createMinistryRequest) {
        System.out.println("Create Controller");
        Ministry newMinistry = _modelMapper.map(createMinistryRequest, Ministry.class);
        return ministryService.create(newMinistry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ministryService.delete(id));
    }

    @PutMapping(path = "/{id}")
    public boolean updateMinistryById(@PathVariable(value = "id") Long id,
            @Valid @RequestBody EditMinistryRequest editMinistryRequest) {

        Ministry updateMinistry = _modelMapper.map(editMinistryRequest, Ministry.class);
        return ministryService.update(id, updateMinistry);
    }

}
