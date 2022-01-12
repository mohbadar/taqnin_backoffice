package com.nsia.officems.gop.accountability;


import org.springframework.http.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.google.gson.Gson;
import com.nsia.officems.gop.accountability.dto.Accountabilitydto;
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
@RequestMapping("/api/accountabilities")
public class AccountablityController {
    @Autowired
    private AccountablityService accountablityService;

    @GetMapping()
    public List<Accountability> findAll(){
        return accountablityService.findAll();
    }

    @GetMapping("/profile")
    public List<Accountability> getAccountabilityByProfile(@RequestParam("pId") Long pId ) {
        return accountablityService.findByProfile_id(pId);
    }

    @GetMapping(value = "/{rewardId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "rewardId", required = true) long id) {
        System.out.println("Objection " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        Accountability objection = accountablityService.findById(id);
        data.put("objection", objection);
        return ResponseEntity.ok(data);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        Gson g = new Gson();
        Accountabilitydto dto = g.fromJson(data, Accountabilitydto.class);
        return ResponseEntity.ok(accountablityService.update(id, dto));
        
	}

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Accountability> create(@RequestParam("data") String data, HttpServletRequest request) throws Exception 
    {
        Gson g = new Gson();
        Accountabilitydto dto = g.fromJson(data, Accountabilitydto.class);
        return ResponseEntity.ok(accountablityService.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(accountablityService.delete(id));
    }
    
}
