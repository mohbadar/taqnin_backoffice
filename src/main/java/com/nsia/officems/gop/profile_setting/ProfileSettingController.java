package com.nsia.officems.gop.profile_setting;


import org.springframework.http.MediaType;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profilesettings")
public class ProfileSettingController {

    @Autowired
    private ProfileSettingService profileSettingService;

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        Gson g = new Gson();
        ProfileSetting profileSetting = g.fromJson(data, ProfileSetting.class);
        return ResponseEntity.ok(profileSettingService.create(id, profileSetting));
        
	}
    
}
