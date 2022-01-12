package com.nsia.officems._admin.department;

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

import java.util.List;

import javax.validation.Valid;

import com.nsia.officems._admin.department.requests.CreateDepartmentRequest;
import com.nsia.officems._admin.department.requests.EditDepartmentRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/admin/departments")
public class DepartmentController {

    private final ModelMapper _modelMapper;

    @Autowired
    private DepartmentService departmentService;

    @PreAuthorize("hasAuthority('DEPARTMENT_LIST')")
    @GetMapping()
    public List<Department> findAll() {
        return departmentService.findAll();
    }

    @PreAuthorize("hasAuthority('DEPARTMENT_VIEW')")
    @GetMapping(path = { "/{id}" })
    public Department findById(@PathVariable("id") Long id) {
        return departmentService.findById(id);
    }

    @PreAuthorize("hasAuthority('DEPARTMENT_CREATE')")
    @PostMapping()
    public Department create(@RequestBody CreateDepartmentRequest createDepartmentRequest) {
        System.out.println("Create Controller");
        Department newDepartment = _modelMapper.map(createDepartmentRequest, Department.class);
        return departmentService.create(newDepartment);
    }

    @PreAuthorize("hasAuthority('DEPARTMENT_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(departmentService.delete(id));
    }

    @PreAuthorize("hasAuthority('DEPARTMENT_EDIT')")
    @PutMapping(path = "/{id}")
    public boolean updateDepartmentById(@PathVariable(value = "id") Long id,
            @Valid @RequestBody EditDepartmentRequest editDepartmentRequest) {

        Department updateDepartment = _modelMapper.map(editDepartmentRequest, Department.class);
        return departmentService.update(id, updateDepartment);
    }

}
