package com.nsia.officems.gop.profile_retirement_type;

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
@RequestMapping(value = "/api/profileretirementtypes")
public class ProfileRetirementTypeController {
    
    @Autowired
    private ProfileRetirementTypeService profileResignationTypeService;

    @GetMapping()
    public List<ProfileRetirementType> findAll(){
        return profileResignationTypeService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ProfileRetirementType findById(@PathVariable("id") Long id){
        return profileResignationTypeService.findById(id);
    }

    @PostMapping()
    public ProfileRetirementType create(@RequestBody ProfileRetirementType level ){
        System.out.println("Create Controller");
        return profileResignationTypeService.create(level);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(profileResignationTypeService.delete(id));
    }
}
