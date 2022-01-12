package com.nsia.officems.gop.employeePosition;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/employeepositions")
public class EmployeePositionController {
    @Autowired
    private EmployeePositionService employeePositionService;

    @GetMapping()
    public List<EmployeePosition> findAll(){
        return employeePositionService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public EmployeePosition findById(@PathVariable("id") Long id){
        return employeePositionService.findById(id);
    }

    @PostMapping()
    public EmployeePosition create(@RequestBody EmployeePosition position ){
        System.out.println("Create Controller");
        return employeePositionService.create(position);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(employeePositionService.delete(id));
    }
    
}
