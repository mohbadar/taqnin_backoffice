package com.nsia.officems.gop.salary;


import org.springframework.http.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/api/salaries")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;

    @GetMapping()
    public List<Salary> findAll(){
        return salaryService.findAll();
    }

    @GetMapping("/profile")
    public List<Salary> getSalaryByProfile(@RequestParam("pId") Long pId ) {
        return salaryService.findByProfile_id(pId);
    }

    @GetMapping(value = "/{publicaitonId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "publicaitonId", required = true) long id) {
        System.out.println("Objection " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        Salary objection = salaryService.findById(id);
        data.put("objection", objection);
        return ResponseEntity.ok(data);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        return ResponseEntity.ok(salaryService.update(id, data));
        
	}

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Salary> create(@RequestParam("data") String data, HttpServletRequest request) throws Exception 
    {
        return ResponseEntity.ok(salaryService.create(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(salaryService.delete(id));
    }
    
}
