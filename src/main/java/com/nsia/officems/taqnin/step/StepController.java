package com.nsia.officems.taqnin.step;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/taqnin/steps")
public class StepController {

    @Autowired
    private StepService service;

    @GetMapping()
    public List<Step> findAll(){
        return service.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public Step findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PostMapping()
    public Step create(@RequestBody Step step ){
        return service.create(step);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Step> update(@PathVariable("id") Long id, @RequestBody String step) {
        Gson g = new Gson();
        Step c = g.fromJson(step, Step.class);
        if(c != null) {
            return ResponseEntity.ok(service.update(id, c));
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

}
