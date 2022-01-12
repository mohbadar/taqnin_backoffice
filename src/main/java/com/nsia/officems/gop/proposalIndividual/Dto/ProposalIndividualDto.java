package com.nsia.officems.gop.proposalIndividual.Dto;

import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.proposal.Proposal;
import com.nsia.officems.gop.suggestionSubject.SuggestionSubject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProposalIndividualDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String suggestionNo;
    private SuggestionSubject suggestionSubject;
    private Proposal proposal;
    private Profile profile;
    private Boolean deleted;

}
