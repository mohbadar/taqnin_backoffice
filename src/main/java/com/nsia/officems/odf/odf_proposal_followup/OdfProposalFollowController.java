package com.nsia.officems.odf.odf_proposal_followup;


import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.nsia.officems.odf.odf_proposal_followup.dto.OdfProposalFollowDto;

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
@RequestMapping("/api/odf/proposalfollows")
public class OdfProposalFollowController {
    
    @Autowired
    private OdfProposalFollowService service;

    
    @GetMapping()
    public List<OdfProposalFollow> findAll(){
        return service.findAll();
    }

    @GetMapping("/proposal")
    public List<OdfProposalFollow> getFollowupByProposal(@RequestParam("pId") Long pId ) {
        return service.findByProposal_id(pId);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "id", required = true) long id) {
        System.out.println("Objection " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        OdfProposalFollow objection = service.findById(id);
        data.put("objection", objection);
        return ResponseEntity.ok(data);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        Gson g = new Gson();
        OdfProposalFollowDto dto = g.fromJson(data, OdfProposalFollowDto.class);
        return ResponseEntity.ok(service.update(id, dto));
        
	}

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<OdfProposalFollow> create(@RequestParam("data") String data, HttpServletRequest request) throws Exception 
    {
        Gson g = new Gson();
        OdfProposalFollowDto dto = g.fromJson(data, OdfProposalFollowDto.class);
        return ResponseEntity.ok(service.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
