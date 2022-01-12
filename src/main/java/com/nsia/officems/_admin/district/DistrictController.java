package com.nsia.officems._admin.district;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.nsia.officems._admin.district.Dto.DistrictDto;
import com.nsia.officems._admin.province.Province;
import com.nsia.officems._admin.province.ProvinceService;
import com.nsia.officems._admin.province.Dto.ProvinceDto;

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

@RestController
@RequestMapping("/api/districts")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @Autowired
    private ProvinceService provinceServices;

    ObjectMapper mapper = new ObjectMapper();

    @GetMapping()
    public List<District> findAll(){
        return districtService.findAll();
    }

    @GetMapping("province")
    public List<District> getDistrictsByProvince(@RequestParam("pId") Long provinceId ) {
        return districtService.findByProvince(provinceId);
    }

    @GetMapping(path = {"/{id}"})
    public ObjectNode findById(@PathVariable("id") Long id){
        ObjectNode districtObj = mapper.createObjectNode();
        
        District district = districtService.findById(id);
        List<Province> allProvinces = provinceServices.findAll();

        JsonNode districtJson = mapper.valueToTree(district);
        JsonNode allProvincesJson = mapper.valueToTree(allProvinces);
		districtObj.set("district", districtJson);
		districtObj.set("allProvinces", allProvincesJson);
        return districtObj;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<District> create(@RequestParam("data") String data) throws Exception {
        Gson g = new Gson();
        DistrictDto dto = g.fromJson(data, DistrictDto.class);
        District district = districtService.create(dto);
        System.out.println("info");
        return ResponseEntity.ok(district);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + data);
        Gson g = new Gson();
        DistrictDto dto = g.fromJson(data, DistrictDto.class);
        return ResponseEntity.ok(districtService.update(id, dto));
        
	}


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(districtService.delete(id));
    }    

}
