package com.nsia.officems.gop.promotion_type;

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
@RequestMapping(value = "/api/promotion-type")
public class PromotionTypeController {

    @Autowired
    private PromotionTypeService promotionTypeService;

    @GetMapping()
    public List<PromotionType> findAll(){
        return promotionTypeService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public PromotionType findById(@PathVariable("id") Long id){
        return promotionTypeService.findById(id);
    }

    @PostMapping()
    public PromotionType create(@RequestBody PromotionType promotionType ){
        System.out.println("Create Controller");
        return promotionTypeService.create(promotionType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PromotionType> update(@PathVariable("id") Long id, @RequestBody String promotionType) {
        Gson g = new Gson();
        PromotionType c = g.fromJson(promotionType, PromotionType.class);
        if(c != null) {
            return ResponseEntity.ok(promotionTypeService.update(id, c));
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(promotionTypeService.delete(id));
    }
}
