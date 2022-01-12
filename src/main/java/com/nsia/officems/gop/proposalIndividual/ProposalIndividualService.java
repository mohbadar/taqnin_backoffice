package com.nsia.officems.gop.proposalIndividual;

import java.util.List;

import com.nsia.officems.gop.proposal.Proposal;
import com.nsia.officems.gop.proposalIndividual.Dto.ProposalIndividualDto;

public interface ProposalIndividualService {
    public List<ProposalIndividual> findAll();

    public List<ProposalIndividual> findByProposalId(Proposal proposal);

    public ProposalIndividual create(ProposalIndividualDto[] dto, Proposal prop);

    public ProposalIndividual update(ProposalIndividualDto[] dto, Proposal prop);
}
