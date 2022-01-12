package com.nsia.officems.gop.ethnic;
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
@RequestMapping(value = "/api/ethnicities")
public class EthnicController {

    @Autowired
    private EthnicService ethnicService;

    @GetMapping()
    public List<Ethnic> findAll(){
        return ethnicService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public Ethnic findById(@PathVariable("id") Long id){
        return ethnicService.findById(id);
    }

    @PostMapping()
    public Ethnic create(@RequestBody Ethnic ethnic ){
        System.out.println("Create Controller");
        return ethnicService.create(ethnic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ethnicService.delete(id));
    }
}