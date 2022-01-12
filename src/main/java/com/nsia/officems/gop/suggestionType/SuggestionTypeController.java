package com.nsia.officems.gop.suggestionType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.nsia.officems._util.LookupProjection;


@RestController
@RequestMapping(value = "/api/suggestion-types")
public class SuggestionTypeController {
    @Autowired
    private SuggestionTypeService suggestionService;
    
    @GetMapping("/all")
    public List<LookupProjection> findAll() {
        return suggestionService.findAll();
    }

}
