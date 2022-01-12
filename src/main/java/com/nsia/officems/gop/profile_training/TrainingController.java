package com.nsia.officems.gop.profile_training;


import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import com.google.gson.Gson;
import com.nsia.officems.gop.profile.Dto.RevisionDTO;
import com.nsia.officems.gop.profile_training.dto.TrainingDto;
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
@RequestMapping("/api/trainings")
public class TrainingController {

    
    @Autowired
    private TrainingService trainingService;

    @GetMapping()
    public List<Training> findAll(){
        return trainingService.findAll();
    }

    @GetMapping("/profile")
    public List<Training> getTrainingByProfile(@RequestParam("pId") Long pId ) {
        return trainingService.findByProfile_id(pId);
    }

    @GetMapping(value = "/{publicaitonId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "publicaitonId", required = true) long id) {
        System.out.println("Objection " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        Training objection = trainingService.findById(id);
        data.put("objection", objection);
        return ResponseEntity.ok(data);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        Gson g = new Gson();
        TrainingDto dto = g.fromJson(data, TrainingDto.class);
        return ResponseEntity.ok(trainingService.update(id, dto));
        
	}

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Training> create(@RequestParam("data") String data, HttpServletRequest request) throws Exception 
    {
        Gson g = new Gson();
        TrainingDto dto = g.fromJson(data, TrainingDto.class);
        return ResponseEntity.ok(trainingService.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(trainingService.delete(id));
    }

    @GetMapping(value = "/{id}/history", produces = MediaType.ALL_VALUE)
    public ResponseEntity<List<RevisionDTO>> getHistory(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(trainingService.getTrainingLog(id));
    }
    
}
