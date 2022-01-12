package com.nsia.officems.gop.panelty;


import org.springframework.http.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.google.gson.Gson;
import com.nsia.officems.gop.panelty.dto.PaneltyDto;
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
@RequestMapping("/api/panelties")
public class PaneltyController {

    @Autowired
    private PaneltyService paneltyService;

    @GetMapping()
    public List<Panelty> findAll(){
        return paneltyService.findAll();
    }

    @GetMapping("/profile")
    public List<Panelty> getRewardByProfile(@RequestParam("pId") Long pId ) {
        return paneltyService.findByProfile_id(pId);
    }

    @GetMapping(value = "/{paneltyId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "paneltyId", required = true) long id) {
        System.out.println("Objection " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        Panelty objection = paneltyService.findById(id);
        data.put("objection", objection);
        return ResponseEntity.ok(data);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        Gson g = new Gson();
        PaneltyDto dto = g.fromJson(data, PaneltyDto.class);
        return ResponseEntity.ok(paneltyService.update(id, dto));
        
	}

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Panelty> create(@RequestParam("data") String data, HttpServletRequest request) throws Exception 
    {
        Gson g = new Gson();
        PaneltyDto dto = g.fromJson(data, PaneltyDto.class);
        return ResponseEntity.ok(paneltyService.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(paneltyService.delete(id));
    }
    
}
