package com.nsia.officems.gop.proposalIndividual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.google.gson.Gson;
import com.nsia.officems.gop.proposalIndividual.Dto.ProposalIndividualDto;



@RestController
@RequestMapping(value = "/api/proposalIndividual")
public class ProposalIndividualController {
    @Autowired
    private ProposalIndividualService profileService;

    


    @GetMapping("/all")
    public List<ProposalIndividual> findAll(){
        return profileService.findAll();
    }

    // @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    // public @ResponseBody ResponseEntity<ProposalIndividual> create(@RequestParam("data") String data) throws Exception 
    // {
    //     Gson g = new Gson();
    //     ProposalIndividualDto dto = g.fromJson(data, ProposalIndividualDto.class);
    //     return ResponseEntity.ok(profileService.create(dto));
    // }
}
