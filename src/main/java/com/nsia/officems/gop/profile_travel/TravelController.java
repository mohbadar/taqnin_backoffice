package com.nsia.officems.gop.profile_travel;


import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import com.google.gson.Gson;
import com.nsia.officems.gop.profile_travel.dto.TravelDto;
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
@RequestMapping("/api/travels")
public class TravelController {

    @Autowired
    private TravelService travelService;

    @GetMapping()
    public List<Travel> findAll(){
        return travelService.findAll();
    }

    @GetMapping("/profile")
    public List<Travel> getTravelByProfile(@RequestParam("pId") Long pId ) {
        return travelService.findByProfile_id(pId);
    }

    @GetMapping(value = "/{publicaitonId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "publicaitonId", required = true) long id) {
        System.out.println("Objection " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        Travel objection = travelService.findById(id);
        data.put("objection", objection);
        return ResponseEntity.ok(data);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        Gson g = new Gson();
        TravelDto dto = g.fromJson(data, TravelDto.class);
        return ResponseEntity.ok(travelService.update(id, dto));
        
	}

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Travel> create(@RequestParam("data") String data, HttpServletRequest request) throws Exception 
    {
        Gson g = new Gson();
        TravelDto dto = g.fromJson(data, TravelDto.class);
        return ResponseEntity.ok(travelService.create(dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(travelService.delete(id));
    }
    
}
