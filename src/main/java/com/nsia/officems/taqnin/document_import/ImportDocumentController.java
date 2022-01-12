package com.nsia.officems.taqnin.document_import;

import com.google.gson.Gson;
import com.nsia.officems.taqnin.document.DocumentService;
import com.nsia.officems.taqnin.document_import.dto.ImportDocumentDto;
import com.nsia.officems.taqnin.document_import.dto.ImportDocumentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/taqnin/document/imports")
public class ImportDocumentController {

    @Autowired
    private ImportDocumentService service;

    @Autowired
    private DocumentService documentService;



    @GetMapping(path = { "/{id}" })
    public List<ImportDocument> findAllByDocument(@PathVariable("id") Long id) {
        return service.findByDocumentId(id);
    }

    @GetMapping(path = { "single/{id}" })
    public ImportDocument findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping()
    public ImportDocument create(@RequestBody ImportDocumentDto dto) {
        return service.create(ImportDocumentMapper.MapImportDocumentDto(new ImportDocument(), dto, documentService));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImportDocument> update(@PathVariable("id") Long id, @RequestBody String data) {
        Gson g = new Gson();
        ImportDocument c = g.fromJson(data, ImportDocument.class);
        if (c != null) {
            return ResponseEntity.ok(service.update(id, c));
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

}
