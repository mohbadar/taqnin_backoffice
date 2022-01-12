package com.nsia.officems.taqnin.dochistory.impl;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.taqnin.dochistory.Dochistory;
import com.nsia.officems.taqnin.dochistory.DochistoryRepository;
import com.nsia.officems.taqnin.dochistory.DochistoryService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DochistoryServiceImpl implements DochistoryService {
    @Value("${app.upload.taqnin.document}")
    private String uploadDir;

    
    @Autowired
    private DochistoryRepository repo;

    @Autowired
    UserService userService;

    @Override
    public List<Dochistory> findAll() {
        return repo.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public List<Dochistory> findByDocumentId(Long id){
        return repo.findByDocument_idAndDeletedFalse(id);
    }

    @Override
    public Dochistory findById(Long id) {
        Optional<Dochistory> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Dochistory create(Dochistory dochistory) {
        dochistory.setDeleted(false);
        dochistory.setUser(userService.getLoggedInUser());

        return repo.save(dochistory);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Dochistory> oDecision = repo.findById(id);

        if (oDecision.isPresent()) {
            Dochistory dochistory = oDecision.get();
            dochistory.setDeleted(true);
            dochistory.setDeletedBy(userService.getLoggedInUser().getUsername());
            dochistory.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            repo.save(dochistory);
            return true;
        }

        return false;
    }

    @Override
    public Dochistory update(Long id, Dochistory dochistory) {
        if(id != null ) {
            Optional<Dochistory> newDecision = repo.findById(id);
            if(newDecision.isPresent())
            {   Dochistory updatedDochistory = newDecision.get();
                updatedDochistory.setDate(dochistory.getDate());
                updatedDochistory.setNumber(dochistory.getNumber());
                return repo.save(updatedDochistory);
            }
            return null;

        }

        return null;
    }

    @Override
    public File downloadAttachment(Long id, Long docId) throws Exception {
        Optional<Dochistory> document = repo.findById(id);
        if (document.isPresent()) {

            String fileName = document.get().getFileName();

            String saveDirectory = uploadDir + "/" + docId +"_v/" + fileName;
            if (new File(saveDirectory).exists()) {
                return new File(saveDirectory);
            }
        }

        return null;
    }
}
