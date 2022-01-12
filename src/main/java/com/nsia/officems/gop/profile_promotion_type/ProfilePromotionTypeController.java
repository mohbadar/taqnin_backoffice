package com.nsia.officems.gop.profile_promotion_type;


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
@RequestMapping(value = "/api/profilepromotiontypes")
public class ProfilePromotionTypeController {
    @Autowired
    private ProfilePromotionTypeService profilePromotionTypeService;

    @GetMapping()
    public List<ProfilePromotionType> findAll(){
        return profilePromotionTypeService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ProfilePromotionType findById(@PathVariable("id") Long id){
        return profilePromotionTypeService.findById(id);
    }

    @PostMapping()
    public ProfilePromotionType create(@RequestBody ProfilePromotionType level ){
        System.out.println("Create Controller");
        return profilePromotionTypeService.create(level);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(profilePromotionTypeService.delete(id));
    }
}
