package com.nsia.officems.gop.decree_document_type.impl;

import java.util.List;

import com.nsia.officems._util.LookupProjection;
import com.nsia.officems.gop.decree_document_type.DecreeDocumentType;
import com.nsia.officems.gop.decree_document_type.DecreeDocumentTypeRepository;
import com.nsia.officems.gop.decree_document_type.DecreeDocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DecreeDocumentTypeServiceImpl implements DecreeDocumentTypeService {

    @Autowired
    private DecreeDocumentTypeRepository decreeDocumentTypeRepository;

    @Override
    public List<LookupProjection> findAll() {
        String lang = "dr";

        switch (lang) {
            case "en":
                return decreeDocumentTypeRepository.getByNameEn();
            case "dr":
                return decreeDocumentTypeRepository.getByNameFa();
            case "ps":
                return decreeDocumentTypeRepository.getByNamePs();
            default:
                return decreeDocumentTypeRepository.getByNameEn();
        }
    }

    @Override
    public DecreeDocumentType findById(Long id) {
        return decreeDocumentTypeRepository.findById(id).get();
    }
}
