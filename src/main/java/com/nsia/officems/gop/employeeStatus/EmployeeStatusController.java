package com.nsia.officems.gop.employeeStatus;

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
@RequestMapping(value = "/api/employeestatus")
public class EmployeeStatusController {

    @Autowired
    private EmployeeStatusService employeeStatusService;

    @GetMapping()
    public List<EmployeeStatus> findAll(){
        return employeeStatusService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public EmployeeStatus findById(@PathVariable("id") Long id){
        return employeeStatusService.findById(id);
    }

    @PostMapping()
    public EmployeeStatus create(@RequestBody EmployeeStatus status ){
        System.out.println("Create Controller");
        return employeeStatusService.create(status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeStatusService.delete(id));
    }
    
}
