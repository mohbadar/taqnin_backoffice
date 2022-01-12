package com.nsia.officems.gop.documentType.impl;

import java.util.List;

import com.nsia.officems._util.LookupProjection;
import com.nsia.officems.gop.documentType.DocumentType;
import com.nsia.officems.gop.documentType.DocumentTypeRepository;
import com.nsia.officems.gop.documentType.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Override
    public List<LookupProjection> findAll() {
        String lang = "dr";

        switch (lang) {
        case "en":
            return documentTypeRepository.getByNameEn();
        case "dr":
            return documentTypeRepository.getByNameFa();
        case "ps":
            return documentTypeRepository.getByNamePs();
        default:
            return documentTypeRepository.getByNameEn();
        }
    }

    @Override
    public DocumentType findById(Long id) {
        return documentTypeRepository.findById(id).get();
    }
}
