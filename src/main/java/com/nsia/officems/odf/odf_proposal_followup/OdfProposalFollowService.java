package com.nsia.officems.odf.odf_proposal_followup;

import java.util.List;

import com.nsia.officems.odf.odf_proposal_followup.dto.OdfProposalFollowDto;

public interface OdfProposalFollowService {
    public List<OdfProposalFollow> findAll();
    public OdfProposalFollow findById(Long id);
    public OdfProposalFollow create(OdfProposalFollowDto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, OdfProposalFollowDto dto);
    public List<OdfProposalFollow> findByProposal_id(Long id); 
}
