package com.nsia.officems.gop.paneltyType;

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
@RequestMapping(value = "/api/paneltytypes")
public class PaneltyTypeController {

    @Autowired
    private PaneltyTypeService paneltyTypeService;

    @GetMapping()
    public List<PaneltyType> findAll(){
        return paneltyTypeService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public PaneltyType findById(@PathVariable("id") Long id){
        return paneltyTypeService.findById(id);
    }

    @PostMapping()
    public PaneltyType create(@RequestBody PaneltyType type ){
        System.out.println("Create Controller");
        return paneltyTypeService.create(type);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(paneltyTypeService.delete(id));
    }
    
}
