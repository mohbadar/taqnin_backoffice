package com.nsia.officems.gop.Complaints_Docs_type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/complaintdocstype")
public class ComplaintDocsTypeController {

    @Autowired
    private ComplaintDocsTypeService complaintDocsTypeService;

    @GetMapping(" ")
    public List<ComplaintDocsType> findAll() {
        System.out.println("******************************");
        return complaintDocsTypeService.findAll();

    }

    @GetMapping(path = { "/{id}" })
    public ComplaintDocsType findById(@PathVariable("id") Long id) {
        return complaintDocsTypeService.findById(id);
    }

    @PostMapping()
    public ComplaintDocsType create(@RequestBody ComplaintDocsType type) {
        System.out.println("Create Controller");
        return complaintDocsTypeService.create(type);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(complaintDocsTypeService.delete(id));
    }

}
