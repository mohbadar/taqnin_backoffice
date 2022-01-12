package com.nsia.officems._admin.document_type.impl;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nsia.officems._admin.document_type.DocType;
import com.nsia.officems._admin.document_type.DocTypeRepository;
import com.nsia.officems._admin.document_type.DocTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocTypeServiceImpl implements DocTypeService {
    @Autowired
    private DocTypeRepository doctypeRepository;

    @Override
    public List<DocType> findAll() {
        return doctypeRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public DocType findById(Long id) {
        Optional<DocType> optionalObj = doctypeRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public DocType create(DocType doctype) {
        doctype.setDeleted(false);
        return doctypeRepository.save(doctype);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<DocType> doctype = doctypeRepository.findById(id);

        if (doctype.isPresent()) {
            DocType org2 = doctype.get();
            org2.setDeleted(true);
            // ethnic.setDeletedBy(userService.getLoggedInUser().getUsername());
            org2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            doctypeRepository.save(org2);
            return true;
        }

        return false;
    }

    @Override
    public boolean update(Long id, DocType org) {
        Optional<DocType> orgToBeUpdated = doctypeRepository.findById(id);
        if (orgToBeUpdated.isEmpty()) {
            return false;
        }

        DocType editedOrg = orgToBeUpdated.get();

        editedOrg.setNameEn(org.getNameEn());
        editedOrg.setNameDr(org.getNameDr());
        editedOrg.setNamePs(org.getNamePs());

        doctypeRepository.save(editedOrg);
        return true;
    }

}
