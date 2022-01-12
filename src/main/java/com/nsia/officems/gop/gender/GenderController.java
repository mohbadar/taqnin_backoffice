package com.nsia.officems.gop.gender;


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
@RequestMapping(value = "/api/genders")
public class GenderController {
    @Autowired
    private GenderService genderService;

    @GetMapping()
    public List<Gender> findAll(){
        return genderService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public Gender findById(@PathVariable("id") Long id){
        return genderService.findById(id);
    }

    @PostMapping()
    public Gender create(@RequestBody Gender gender ){
        System.out.println("Create Controller");
        return genderService.create(gender);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(genderService.delete(id));
    }
    
}
