package com.nsia.officems.gop.sect;


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
@RequestMapping(value = "/api/sects")
public class SectController {

    
    @Autowired
    private SectService sectService;

    @GetMapping()
    public List<Sect> findAll(){
        return sectService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public Sect findById(@PathVariable("id") Long id){
        return sectService.findById(id);
    }

    @PostMapping()
    public Sect create(@RequestBody Sect sect ){
        System.out.println("Create Controller");
        return sectService.create(sect);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(sectService.delete(id));
    }
    
}
