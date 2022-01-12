package com.nsia.officems.gop.decree_document_type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.nsia.officems._util.LookupProjection;

@RestController
@RequestMapping(value = "/api/decree-document-types")
public class DecreeDocumentTypeController {
    @Autowired
    private DecreeDocumentTypeService documentTypeService;

    @GetMapping("/all")
    public List<LookupProjection> findAll() {
        return documentTypeService.findAll();
    }
}
