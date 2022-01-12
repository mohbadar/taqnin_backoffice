package com.nsia.officems._admin.commission;

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
import com.nsia.officems._admin.commission.requests.CreateCommissionRequest;
import com.nsia.officems._admin.commission.requests.EditCommissionRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/commissions")
public class CommissionController {

    private final ModelMapper _modelMapper;

    @Autowired
    private CommissionService commissionService;

    @GetMapping()
    public List<Commission> findAll() {
        return commissionService.findAll();
    }

    @GetMapping(path = { "/{id}" })
    public Commission findById(@PathVariable("id") Long id) {
        return commissionService.findById(id);
    }

    @PostMapping()
    public Commission create(@RequestBody CreateCommissionRequest createCommissionRequest) {
        System.out.println("Create Controller");
        Commission newCommission = _modelMapper.map(createCommissionRequest, Commission.class);
        return commissionService.create(newCommission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(commissionService.delete(id));
    }

    @PutMapping(path = "/{id}")
    public boolean updateCommissionById(@PathVariable(value = "id") Long id,
            @Valid @RequestBody EditCommissionRequest editCommissionRequest) {
        Commission updateCommission = _modelMapper.map(editCommissionRequest, Commission.class);
        return commissionService.update(id, updateCommission);
    }

}
