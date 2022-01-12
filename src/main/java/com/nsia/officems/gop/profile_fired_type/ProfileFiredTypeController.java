package com.nsia.officems.gop.profile_fired_type;


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
@RequestMapping(value = "/api/profilefiredtypes")
public class ProfileFiredTypeController {

    @Autowired
    private ProfileFiredTypeService profileFiredTypeService;

    @GetMapping()
    public List<ProfileFiredType> findAll(){
        return profileFiredTypeService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ProfileFiredType findById(@PathVariable("id") Long id){
        return profileFiredTypeService.findById(id);
    }

    @PostMapping()
    public ProfileFiredType create(@RequestBody ProfileFiredType level ){
        System.out.println("Create Controller");
        return profileFiredTypeService.create(level);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(profileFiredTypeService.delete(id));
    }
    
}
