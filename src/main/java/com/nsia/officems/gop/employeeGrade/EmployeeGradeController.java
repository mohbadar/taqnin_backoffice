package com.nsia.officems.gop.employeeGrade;
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
@RequestMapping(value = "/api/employeegrades")
public class EmployeeGradeController {
    @Autowired
    private EmployeeGradeService employeeGradeService;

    @GetMapping()
    public List<EmployeeGrade> findAll(){
        return employeeGradeService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public EmployeeGrade findById(@PathVariable("id") Long id){
        return employeeGradeService.findById(id);
    }

    @PostMapping()
    public EmployeeGrade create(@RequestBody EmployeeGrade grade ){
        System.out.println("Create Controller");
        return employeeGradeService.create(grade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeGradeService.delete(id));
    }
    
}
