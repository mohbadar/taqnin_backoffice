package com.nsia.officems.gop.religion;


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
@RequestMapping(value = "/api/religions")
public class ReligionController {
    
    @Autowired
    private ReligionService religionService;

    @GetMapping()
    public List<Religion> findAll(){
        return religionService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public Religion findById(@PathVariable("id") Long id){
        return religionService.findById(id);
    }

    @PostMapping()
    public Religion create(@RequestBody Religion religion ){
        System.out.println("Create Controller");
        return religionService.create(religion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(religionService.delete(id));
    }
    
}
