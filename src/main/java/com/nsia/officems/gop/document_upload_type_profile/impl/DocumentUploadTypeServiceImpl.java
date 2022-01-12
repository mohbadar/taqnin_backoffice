package com.nsia.officems.gop.document_upload_type_profile.impl;

import com.nsia.officems.gop.document_upload_type_profile.DocumentUploadType;
import com.nsia.officems.gop.document_upload_type_profile.DocumentUploadTypeRepository;
import com.nsia.officems.gop.document_upload_type_profile.DocumentUploadTypeService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class DocumentUploadTypeServiceImpl implements DocumentUploadTypeService{
    @Autowired
    private DocumentUploadTypeRepository documentUploadTypeRepository;

    @Override
    public List<DocumentUploadType> findAll() {
        return documentUploadTypeRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public DocumentUploadType findById(Long id) {
        Optional<DocumentUploadType> optionalObj = documentUploadTypeRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public DocumentUploadType create(DocumentUploadType type) {
        return documentUploadTypeRepository.save(type);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<DocumentUploadType> type = documentUploadTypeRepository.findById(id);

        if (type.isPresent()) {
            DocumentUploadType type2 = type.get();
            type2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            type2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            documentUploadTypeRepository.save(type2);
            return true;
        }

        return false;
    }
    
}
