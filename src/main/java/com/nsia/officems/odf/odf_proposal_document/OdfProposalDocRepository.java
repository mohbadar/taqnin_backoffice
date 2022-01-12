package com.nsia.officems.odf.odf_proposal_document;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OdfProposalDocRepository extends JpaRepository<OdfProposalDoc, Long> {
    public List<OdfProposalDoc> findByProposal_id(Long id);

}
