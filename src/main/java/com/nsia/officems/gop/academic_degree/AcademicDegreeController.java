package com.nsia.officems.gop.academic_degree;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.nsia.officems.gop.academic_degree.AcademicDegree;
import com.nsia.officems.gop.academic_degree.dto.AcademicDegreeDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@RequestMapping("/api/academicdegrees")
public class AcademicDegreeController {

    @Autowired
    private AcademicDegreeService academicDegreeService;

    @GetMapping()
    public List<AcademicDegree> findAll(){
        return academicDegreeService.findAll();
    }

    @GetMapping("/profile")
    public List<AcademicDegree> getEducationByProfile(@RequestParam("pId") Long pId ) {
        return academicDegreeService.findByProfile_id(pId);
    }

    @GetMapping(value = "/{academicId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "academicId", required = true) long id) {
        System.out.println("Objection " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        AcademicDegree objection = academicDegreeService.findById(id);
        data.put("objection", objection);
        return ResponseEntity.ok(data);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        Gson g = new Gson();
        AcademicDegreeDto dto = g.fromJson(data, AcademicDegreeDto.class);
        return ResponseEntity.ok(academicDegreeService.update(id, dto));
        
	}

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<AcademicDegree> create(@RequestParam("data") String data, HttpServletRequest request) throws Exception 
    {
        Gson g = new Gson();
        AcademicDegreeDto dto = g.fromJson(data, AcademicDegreeDto.class);
        return ResponseEntity.ok(academicDegreeService.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(academicDegreeService.delete(id));
    }
    
    
}
