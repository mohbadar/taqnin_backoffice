package com.nsia.officems.taqnin.document_import.impl;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.taqnin.document_import.ImportDocument;
import com.nsia.officems.taqnin.document_import.ImportDocumentRepository;
import com.nsia.officems.taqnin.document_import.ImportDocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ImportDocumentServiceImpl implements ImportDocumentService {
    @Autowired
    private ImportDocumentRepository repo;

    @Autowired
    UserService userService;

    @Override
    public List<ImportDocument> findByDocumentId(Long id) {
        return repo.findByDocument_idAndDeletedFalse(id);
    }

    @Override
    public ImportDocument findById(Long id) {
        Optional<ImportDocument> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public ImportDocument create(ImportDocument importDocument) {
        
        return repo.save(importDocument);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<ImportDocument> oDecision = repo.findById(id);
        if (oDecision.isPresent()) {
            ImportDocument Decision = oDecision.get();
            Decision.setDeleted(true);
            Decision.setDeletedBy(userService.getLoggedInUser().getUsername());
            Decision.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            repo.save(Decision);
            return true;
        }

        return false;
    }

    @Override
    public ImportDocument update(Long id, ImportDocument importDocument) {
        if(id != null ) {
            Optional<ImportDocument> newDocumentExport = repo.findById(id);
            if(newDocumentExport.isPresent())
            {   ImportDocument updatedComment = newDocumentExport.get();
                updatedComment.setImport_date(importDocument.getImport_date());
                updatedComment.setImport_number(importDocument.getImport_number());
                updatedComment.setBody(importDocument.getBody());
                return repo.save(updatedComment);
            }
        
        }

        return null;
    }
}
