package com.nsia.officems.odf.odf_follow_up_type;

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
@RequestMapping(value = "/api/odf/odffolowuptype")
public class OdfFollowUpTypeController {

    @Autowired
    private OdfFollowUpTypeService service;

    @GetMapping()
    public List<OdfFollowUpType> findAll(){
        return service.findAll();
    }


    @PostMapping()
    public OdfFollowUpType create(@RequestBody OdfFollowUpType type ){
        System.out.println("Create Controller");
        return service.create(type);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
    
}
