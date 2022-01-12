package com.nsia.officems.gop.nationality;

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
@RequestMapping(value = "/api/nationalities")
public class NationalityController {
    
    
    @Autowired
    private NationalityService nationalityService;

    @GetMapping()
    public List<Nationality> findAll(){
        return nationalityService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public Nationality findById(@PathVariable("id") Long id){
        return nationalityService.findById(id);
    }

    @PostMapping()
    public Nationality create(@RequestBody Nationality nationality ){
        System.out.println("Create Controller");
        return nationalityService.create(nationality);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(nationalityService.delete(id));
    }
}
