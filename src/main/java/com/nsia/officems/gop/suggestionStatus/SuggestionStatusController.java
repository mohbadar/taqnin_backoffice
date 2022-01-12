package com.nsia.officems.gop.suggestionStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.nsia.officems._util.LookupProjection;


@RestController
@RequestMapping(value = "/api/suggestion-statuses")
public class SuggestionStatusController {
    @Autowired
    private SuggestionStatusService suggestionStatusService;

    
    @GetMapping("/all")
    public List<LookupProjection> findAll(){
        return suggestionStatusService.findAll();
    }
}
