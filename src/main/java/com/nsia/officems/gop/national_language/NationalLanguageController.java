package com.nsia.officems.gop.national_language;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/nationallanguages")
public class NationalLanguageController {

     
    @Autowired
    private NationalLanguageService languageService;

    @GetMapping()
    public List<NationalLanguage> findAll(){
        return languageService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public NationalLanguage findById(@PathVariable("id") Long id){
        return languageService.findById(id);
    }

    @PostMapping()
    public NationalLanguage create(@RequestBody NationalLanguage language ){
        System.out.println("Create Controller");
        return languageService.create(language);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(languageService.delete(id));
    }
    
}
