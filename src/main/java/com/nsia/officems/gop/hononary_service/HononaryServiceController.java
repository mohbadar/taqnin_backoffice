package com.nsia.officems.gop.hononary_service;


import org.springframework.http.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.google.gson.Gson;
import com.nsia.officems.gop.hononary_service.dto.HononaryServiceDto;
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
@RequestMapping("/api/hononaryservices")
public class HononaryServiceController {

    @Autowired
    private HononaryServiceS hononaryServiceS;

    @GetMapping()
    public List<HononaryService> findAll(){
        return hononaryServiceS.findAll();
    }

    @GetMapping("/profile")
    public List<HononaryService> getHononaryServiceByProfile(@RequestParam("pId") Long pId ) {
        return hononaryServiceS.findByProfile_id(pId);
    }

    @GetMapping(value = "/{hononaryId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "hononaryId", required = true) long id) {
        System.out.println("Objection " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        HononaryService objection = hononaryServiceS.findById(id);
        data.put("objection", objection);
        return ResponseEntity.ok(data);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        Gson g = new Gson();
        HononaryServiceDto dto = g.fromJson(data, HononaryServiceDto.class);
        return ResponseEntity.ok(hononaryServiceS.update(id, dto));
        
	}

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<HononaryService> create(@RequestParam("data") String data, HttpServletRequest request) throws Exception 
    {
        Gson g = new Gson();
        HononaryServiceDto dto = g.fromJson(data, HononaryServiceDto.class);
        return ResponseEntity.ok(hononaryServiceS.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(hononaryServiceS.delete(id));
    }
    
}
