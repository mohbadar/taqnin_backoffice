package com.nsia.officems.gop.academic_degree_type;

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
@RequestMapping(value = "/api/academicdegreetypes")
public class AcademicDegreeTypeController {
    @Autowired
    private AcademicDecreeTypeService academicDecreeTypeService;

    @GetMapping()
    public List<AcademicDecreeType> findAll(){
        return academicDecreeTypeService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public AcademicDecreeType findById(@PathVariable("id") Long id){
        return academicDecreeTypeService.findById(id);
    }

    @PostMapping()
    public AcademicDecreeType create(@RequestBody AcademicDecreeType type ){
        System.out.println("Create Controller");
        return academicDecreeTypeService.create(type);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(academicDecreeTypeService.delete(id));
    }
    
}
