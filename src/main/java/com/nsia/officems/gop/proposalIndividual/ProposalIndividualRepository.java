package com.nsia.officems.gop.proposalIndividual;

import java.util.List;

import com.nsia.officems.gop.proposal.Proposal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalIndividualRepository extends JpaRepository<ProposalIndividual, Long> {
    public List<ProposalIndividual> findByProposal(Proposal proposal);
}
