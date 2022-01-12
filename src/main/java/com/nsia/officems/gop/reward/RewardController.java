package com.nsia.officems.gop.reward;

import org.springframework.http.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.google.gson.Gson;
import com.nsia.officems.gop.profile.Dto.RevisionDTO;
import com.nsia.officems.gop.reward.dto.RewardDto;
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
@RequestMapping("/api/rewards")
public class RewardController {
    @Autowired
    private RewardService rewardService;

    @GetMapping()
    public List<Reward> findAll(){
        return rewardService.findAll();
    }

    @GetMapping("/profile")
    public List<Reward> getRewardByProfile(@RequestParam("pId") Long pId ) {
        return rewardService.findByProfile_id(pId);
    }

    @GetMapping(value = "/{rewardId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "rewardId", required = true) long id) {
        System.out.println("Objection " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        Reward objection = rewardService.findById(id);
        data.put("objection", objection);
        return ResponseEntity.ok(data);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        Gson g = new Gson();
        RewardDto dto = g.fromJson(data, RewardDto.class);
        return ResponseEntity.ok(rewardService.update(id, dto));
        
	}

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Reward> create(@RequestParam("data") String data, HttpServletRequest request) throws Exception 
    {
        Gson g = new Gson();
        RewardDto dto = g.fromJson(data, RewardDto.class);
        return ResponseEntity.ok(rewardService.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(rewardService.delete(id));
    }

    @GetMapping(value = "/{id}/history", produces = MediaType.ALL_VALUE)
    public ResponseEntity<List<RevisionDTO>> getHistory(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(rewardService.getRewardLog(id));
    }
    
}
