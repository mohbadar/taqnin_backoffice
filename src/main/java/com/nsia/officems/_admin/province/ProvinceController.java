package com.nsia.officems._admin.province;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.nsia.officems._admin.country.Country;
import com.nsia.officems._admin.country.CountryService;
import com.nsia.officems._admin.province.Dto.ProvinceDto;

@RestController
@RequestMapping(value = "/api/provinces")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private CountryService countryService;

    ObjectMapper mapper = new ObjectMapper();


    @GetMapping()
    public List<Province> findAll(){
        return provinceService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ObjectNode findById(@PathVariable("id") Long id){
        ObjectNode provinceObj = mapper.createObjectNode();
        
        Province province = provinceService.findById(id);
        List<Country> allCountries = countryService.findAll();

        JsonNode provinceJson = mapper.valueToTree(province);
        JsonNode allCountriesJson = mapper.valueToTree(allCountries);
		provinceObj.set("province", provinceJson);
		provinceObj.set("allCountries", allCountriesJson);
        return provinceObj;
    }

    @GetMapping("/country")
    public List<Province> findByCountry(@RequestParam("cId") Long id) {
        return this.provinceService.findByCountry(id);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Province> create(@RequestParam("data") String data) throws Exception {
        Gson g = new Gson();
        ProvinceDto dto = g.fromJson(data, ProvinceDto.class);
        Province province = provinceService.create(dto);
        System.out.println("info");
        return ResponseEntity.ok(province);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + data);

        Gson g = new Gson();
        ProvinceDto dto = g.fromJson(data, ProvinceDto.class);
        return ResponseEntity.ok(provinceService.update(id, dto));
        
	}


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(provinceService.delete(id));
    }


}
