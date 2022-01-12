package com.nsia.officems.gop.profile_job;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;


import javax.servlet.http.HttpServletRequest;

import com.nsia.officems.gop.profile.Dto.RevisionDTO;

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
@RequestMapping("/api/initialprofilejob")
public class ProfileJobController {
    
    @Autowired
    private ProfileJobService initialProfileJobService;

    @GetMapping("/profile")
    public List<ProfileJob> getByProfile(@RequestParam("pId") Long pId ) {
        return initialProfileJobService.findByProfile_id(pId);
    }

    @GetMapping(value = "/{publicaitonId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "publicaitonId", required = true) long id) {
        System.out.println("Objection " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        ProfileJob objection = initialProfileJobService.findById(id);
        data.put("objection", objection);
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/work/{Id}")
    public ResponseEntity<Map<String, Object>> getWorkExperince(@PathVariable(name = "Id", required = true) long id) {
        Map<String, Object> data = new HashMap<String, Object>();
         data = initialProfileJobService.calculteWorkExperienceByProfile(id);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/profilejob")
    public List<ProfileJob> getbyProfileEndDate(@RequestParam("pId") Long pId ) {
        return initialProfileJobService.findByProfile_idEndDateIsNull(pId);
    }


    @PutMapping(value = "/break/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> updateBreakJob(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        return ResponseEntity.ok(initialProfileJobService.updateBreakJob(id, data));
        
	}

    @PostMapping(value = "/break/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<ProfileJob> createBreakJob(@RequestParam("data") String data, HttpServletRequest request) throws Exception 
    {
        return ResponseEntity.ok(initialProfileJobService.createBreakJob(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(initialProfileJobService.delete(id));
    }

    @GetMapping(value = "/{id}/history", produces = MediaType.ALL_VALUE)
    public ResponseEntity<List<RevisionDTO>> getHistory(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(initialProfileJobService.getProfileJobLog(id));
    }

}
