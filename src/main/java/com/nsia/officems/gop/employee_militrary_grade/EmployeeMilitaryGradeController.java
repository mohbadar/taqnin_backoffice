package com.nsia.officems.gop.employee_militrary_grade;


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
@RequestMapping(value = "/api/employeemilitarygrades")
public class EmployeeMilitaryGradeController {

    @Autowired
    private EmployeeMilitaryGradeService employeeMilitaryGradeService;

    @GetMapping()
    public List<EmployeeMilitaryGrade> findAll(){
        return employeeMilitaryGradeService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public EmployeeMilitaryGrade findById(@PathVariable("id") Long id){
        return employeeMilitaryGradeService.findById(id);
    }

    @PostMapping()
    public EmployeeMilitaryGrade create(@RequestBody EmployeeMilitaryGrade grade ){
        System.out.println("Create Controller");
        return employeeMilitaryGradeService.create(grade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeMilitaryGradeService.delete(id));
    }
    
}
