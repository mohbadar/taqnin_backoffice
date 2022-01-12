package com.nsia.officems._admin.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.google.gson.Gson;


@RestController
@RequestMapping(value = "/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping()
    public List<Country> findAll(){
        return countryService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public Country findById(@PathVariable("id") Long id){
        return countryService.findById(id);
    }

    @PostMapping()
    public Country create(@RequestBody Country country ){
        System.out.println("Create Controller");
        return countryService.create(country);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> update(@PathVariable("id") Long id, @RequestBody String country) {
        Gson g = new Gson();
        Country c = g.fromJson(country, Country.class);
        if(c != null) {
            return ResponseEntity.ok(countryService.update(id, c));
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(countryService.delete(id));
    }
}
