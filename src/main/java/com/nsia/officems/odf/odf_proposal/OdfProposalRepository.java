package com.nsia.officems.odf.odf_proposal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OdfProposalRepository extends JpaRepository<OdfProposal, Long>{
    public List<OdfProposal> findByIdIn(List<Long> proposals);
}
