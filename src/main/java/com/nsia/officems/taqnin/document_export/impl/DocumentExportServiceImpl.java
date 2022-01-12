package com.nsia.officems.taqnin.document_export.impl;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.taqnin.document_export.DocumentExport;
import com.nsia.officems.taqnin.document_export.DocumentExportRepository;
import com.nsia.officems.taqnin.document_export.DocumentExportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentExportServiceImpl implements DocumentExportService {
    @Autowired
    private DocumentExportRepository repo;

    @Autowired
    UserService userService;

    @Override
    public List<DocumentExport> findByDocumentId(Long id) {
        return repo.findByDocument_idAndDeletedFalse(id);
    }

    @Override
    public DocumentExport findById(Long id) {
        Optional<DocumentExport> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public DocumentExport create(DocumentExport documentExport) {
        return repo.save(documentExport);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<DocumentExport> oDecision = repo.findById(id);
        if (oDecision.isPresent()) {
            DocumentExport Decision = oDecision.get();
            Decision.setDeleted(true);
            Decision.setDeletedBy(userService.getLoggedInUser().getUsername());
            Decision.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            repo.save(Decision);
            return true;
        }

        return false;
    }

    @Override
    public DocumentExport update(Long id, DocumentExport documentExport) {
        if(id != null ) {
            Optional<DocumentExport> newDocumentExport = repo.findById(id);
            if(newDocumentExport.isPresent())
            {   DocumentExport updatedComment = newDocumentExport.get();
                updatedComment.setExport_date(documentExport.getExport_date());
                updatedComment.setExport_number(documentExport.getExport_number());
                updatedComment.setBody(documentExport.getBody());
                return repo.save(updatedComment);
            }
        
        }

        return null;
    }
}
