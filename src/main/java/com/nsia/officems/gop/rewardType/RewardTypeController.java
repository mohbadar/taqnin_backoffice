package com.nsia.officems.gop.rewardType;

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
@RequestMapping(value = "/api/rewardtypes")
public class RewardTypeController {

    @Autowired
    private RewardTypeService rewardTypeService;

    @GetMapping()
    public List<RewardType> findAll(){
        return rewardTypeService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public RewardType findById(@PathVariable("id") Long id){
        return rewardTypeService.findById(id);
    }

    @PostMapping()
    public RewardType create(@RequestBody RewardType type ){
        System.out.println("Create Controller");
        return rewardTypeService.create(type);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(rewardTypeService.delete(id));
    }
    
}
