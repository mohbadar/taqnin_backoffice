package com.nsia.officems.gop.Profile_checklist;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profilechecklist")
public class ProfileChecklistController {
    @Autowired
    private ProfileChecklistService profileChecklistService;

    @GetMapping()
    public List<ProfileChecklist> findAll(){
        return profileChecklistService.findAll();
    }

    @GetMapping("/profile")
    public List<ProfileChecklist> getChecklistByProfile(@RequestParam("pId") Long pId ) {
        return profileChecklistService.findByProfile_id(pId);
    }

    @GetMapping(value = "/{checklistId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "checklistId", required = true) long id) {
        System.out.println("Objection " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        ProfileChecklist objection = profileChecklistService.findById(id);
        data.put("objection", objection);
        return ResponseEntity.ok(data);
    }
    
}
