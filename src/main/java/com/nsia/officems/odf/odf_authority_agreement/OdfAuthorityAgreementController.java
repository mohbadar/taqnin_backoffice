package com.nsia.officems.odf.odf_authority_agreement;

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
@RequestMapping(value = "/api/odf/odfauthorityagreements")
public class OdfAuthorityAgreementController {
    @Autowired
    private OdfAuthorityAgreementService odfAuthorityAgreementService;

    @GetMapping()
    public List<OdfAuthorityAgreement> findAll(){
        return odfAuthorityAgreementService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public OdfAuthorityAgreement findById(@PathVariable("id") Long id){
        return odfAuthorityAgreementService.findById(id);
    }

    @PostMapping()
    public OdfAuthorityAgreement create(@RequestBody OdfAuthorityAgreement level ){
        System.out.println("Create Controller");
        return odfAuthorityAgreementService.create(level);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(odfAuthorityAgreementService.delete(id));
    }
}
