package com.nsia.officems.gop.profile_promotion;


import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.nsia.officems.gop.profile_promotion.dto.ProfilePromotionDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profilepromotions")
public class ProfilePromotionController {
    @Autowired
    private ProfilePromotionService profilePromotionService;

    @GetMapping()
    public List<ProfilePromotion> findAll(){
        return profilePromotionService.findAll();
    }

    @GetMapping("/profile")
    public List<ProfilePromotion> getPromotionByProfile(@RequestParam("pId") Long pId ) {
        return profilePromotionService.findByProfile_id(pId);
    }

    @GetMapping("/promotion")
    public ProfilePromotion getLastestPromotionByProfile(@RequestParam("pId") Long pId ) {
        return profilePromotionService.findbyLatestPromotion(pId);
    }

    @GetMapping(value = "/{publicaitonId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "publicaitonId", required = true) long id) {
        System.out.println("Objection " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        ProfilePromotion objection = profilePromotionService.findById(id);
        data.put("objection", objection);
        return ResponseEntity.ok(data);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        Gson g = new Gson();
        ProfilePromotionDto dto = g.fromJson(data, ProfilePromotionDto.class);
        return ResponseEntity.ok(profilePromotionService.update(id, dto));
        
	}

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<ProfilePromotion> create(@RequestParam("data") String data, HttpServletRequest request) throws Exception 
    {
        Gson g = new Gson();
        ProfilePromotionDto dto = g.fromJson(data, ProfilePromotionDto.class);
        return ResponseEntity.ok(profilePromotionService.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(profilePromotionService.delete(id));
    }
    
}
