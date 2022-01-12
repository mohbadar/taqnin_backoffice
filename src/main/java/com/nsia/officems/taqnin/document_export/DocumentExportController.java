package com.nsia.officems.taqnin.document_export;
import com.google.gson.Gson;
import com.nsia.officems.taqnin.document.DocumentService;
import com.nsia.officems.taqnin.document_export.dto.DocumentExportDto;
import com.nsia.officems.taqnin.document_export.dto.DocumentExportMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/taqnin/document/exports")
public class DocumentExportController {

    @Autowired
    private DocumentExportService service;
    
    @Autowired
    private DocumentService documentService;
    @GetMapping(path = {"/{id}"})
    public List<DocumentExport> findAllByDocument(@PathVariable("id") Long id){
        return service.findByDocumentId(id);
    }

    @GetMapping(path = {"single/{id}"})
    public DocumentExport findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PostMapping()
    public DocumentExport create(@RequestBody DocumentExportDto dto ){
        return service.create(DocumentExportMapper.MapDocCommentDto(new DocumentExport(), dto, documentService));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentExport> update(@PathVariable("id") Long id, @RequestBody String data) {
        Gson g = new Gson();
        DocumentExport c = g.fromJson(data, DocumentExport.class);
        if(c != null) {
            return ResponseEntity.ok(service.update(id, c));
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

}
