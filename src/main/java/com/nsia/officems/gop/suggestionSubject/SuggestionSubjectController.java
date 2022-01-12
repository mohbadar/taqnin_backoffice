package com.nsia.officems.gop.suggestionSubject;

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



@RestController
@RequestMapping(value = "/api/suggestionSubject")
public class SuggestionSubjectController {
    @Autowired
    private SuggestionSubjectService suggestionSubjectService;

    @GetMapping("/all")
    public List<SuggestionSubject> findAll(){
        return suggestionSubjectService.findAll();
    }
}
