package com.nsia.officems.odf.odf_proposal_followup;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OdfProposalFollowRepository extends JpaRepository<OdfProposalFollow, Long>{
    public List<OdfProposalFollow> findByProposal_idAndDeletedIsFalseOrDeletedIsNullOrderByDateDesc(Long id);

}
