package com.nsia.officems.odf.odf_proposal_followup.impl;

import com.nsia.officems.odf.odf_proposal_followup.OdfProposalFollow;
import com.nsia.officems.odf.odf_proposal_followup.OdfProposalFollowRepository;
import com.nsia.officems.odf.odf_proposal_followup.OdfProposalFollowService;
import com.nsia.officems.odf.odf_proposal_followup.dto.OdfProposalFollowDto;
import com.nsia.officems.odf.odf_proposal_followup.dto.OdfProposalFollowMapper;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.nsia.officems.odf.odf_proposal.OdfProposalService;

import org.springframework.stereotype.Service;

@Service
public class OdfProposalFollowServiceImpl implements OdfProposalFollowService{
    @Autowired
    private OdfProposalFollowRepository repo;

    @Autowired
    private OdfProposalService service;  


    @Override
    public List<OdfProposalFollow> findAll() {
        return repo.findAll();
    }

    public List<OdfProposalFollow> findByProposal_id(Long id){
        return repo.findByProposal_idAndDeletedIsFalseOrDeletedIsNullOrderByDateDesc(id);
    }

    @Override
    public OdfProposalFollow findById(Long id) {
        Optional<OdfProposalFollow> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public OdfProposalFollow create(OdfProposalFollowDto dto) {
        OdfProposalFollow newFollow = new OdfProposalFollow();
        OdfProposalFollow follow = OdfProposalFollowMapper.MapFollowDto(newFollow, dto, service);
        if (!follow.equals(null)) {
            return repo.save(follow);
        }
        return null;
    }

    @Override
    public Boolean update(Long id, OdfProposalFollowDto dto) {
        Optional<OdfProposalFollow> objection = repo.findById(id);
        if (objection.isPresent()) {
            OdfProposalFollow follow = OdfProposalFollowMapper.MapFollowDto(objection.get(), dto, service);
            if (!follow.equals(null)) {
                repo.save(follow);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<OdfProposalFollow> pOptional = repo.findById(id);

        if (pOptional.isPresent()) {
            OdfProposalFollow proposal = pOptional.get();
            proposal.setDeleted(true);
            proposal.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            repo.save(proposal);
            return true;
        }

        return false;
    }
}
