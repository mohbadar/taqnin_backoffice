package com.nsia.officems.odf.odf_proposal_document_type.impl;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import com.nsia.officems.odf.odf_proposal_document_type.OdfProposalDocTypeService;
import com.nsia.officems.odf.odf_proposal_document_type.OdfProposalDocType;
import com.nsia.officems.odf.odf_proposal_document_type.OdfProposalDocTypeRepository;

import java.time.ZoneId;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class OdfProposalDocServiceTypeImpl implements OdfProposalDocTypeService{
    @Autowired
    private OdfProposalDocTypeRepository repo;

    @Override
    public List<OdfProposalDocType> findAll() {
        return repo.findAll();
    }

    @Override
    public OdfProposalDocType findById(Long id) {
        Optional<OdfProposalDocType> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public OdfProposalDocType create(OdfProposalDocType type) {
        return repo.save(type);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<OdfProposalDocType> type = repo.findById(id);

        if (type.isPresent()) {
            OdfProposalDocType type2 = type.get();
            type2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            type2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            repo.save(type2);
            return true;
        }

        return false;
    }
}
