package com.nsia.officems.taqnin.summary;

import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nsia.officems.gop.complaint.ComplaintService;
import com.nsia.officems.gop.decree.DecreeService;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile_education.Dto.EducationDto;
import com.nsia.officems.gop.proposal.ProposalService;
import com.nsia.officems.taqnin.document.DocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/taqnin/summary")
public class SummaryController {
    @Autowired
    private SummaryService summaryService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(value = "/count-documents")
    public ResponseEntity<Map<String, Object>> countDocuments() {      
      return ResponseEntity.ok(summaryService.countDocuments());
    }

    
}
