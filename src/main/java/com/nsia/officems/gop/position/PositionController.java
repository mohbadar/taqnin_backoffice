package com.nsia.officems.gop.position;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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
@RequestMapping(value = "/api/positionsapi")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping()
    public List<Position> findAll(){
        return positionService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public Position findById(@PathVariable("id") Long id){
        return positionService.findById(id);
    }

    @PostMapping()
    public Position create(@RequestBody Position commission ){
        System.out.println("Create Controller");
        return positionService.create(commission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(positionService.delete(id));
    }
    
}
