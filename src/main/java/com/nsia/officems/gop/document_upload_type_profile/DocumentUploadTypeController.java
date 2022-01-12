package com.nsia.officems.gop.document_upload_type_profile;
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
@RequestMapping(value = "/api/documentuploadtypes")
public class DocumentUploadTypeController {

    @Autowired
    private DocumentUploadTypeService documentUploadTypeService;

    @GetMapping()
    public List<DocumentUploadType> findAll(){
        return documentUploadTypeService.findAll();
    }


    @PostMapping()
    public DocumentUploadType create(@RequestBody DocumentUploadType type ){
        System.out.println("Create Controller");
        return documentUploadTypeService.create(type);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(documentUploadTypeService.delete(id));
    }
    
}
