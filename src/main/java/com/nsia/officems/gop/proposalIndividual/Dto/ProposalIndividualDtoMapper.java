package com.nsia.officems.gop.proposalIndividual.Dto;

import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.proposalIndividual.ProposalIndividual;

public class ProposalIndividualDtoMapper {
    public static ProposalIndividual MapProposalDto(ProposalIndividualDto dto, ProfileService profileService,
            ProposalIndividual profile) {
        if (dto.getId() != null)
            profile.setId(dto.getId());
        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        profile.setFatherName(dto.getFatherName());
        profile.setSuggestionNo(dto.getSuggestionNo());
        profile.setProposal(dto.getProposal());
        profile.setProfile(
                dto.getProfile() != null ? profileService.findByIdWithoutRelation(dto.getProfile().getId()) : null);
        profile.setDeleted(dto.getDeleted());
        return profile;
    }

}
