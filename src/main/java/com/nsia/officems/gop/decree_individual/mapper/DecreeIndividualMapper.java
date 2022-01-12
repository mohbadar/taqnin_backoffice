package com.nsia.officems.gop.decree_individual.mapper;

import com.nsia.officems.gop.decree.Decree;
import com.nsia.officems.gop.decree_individual.DecreeIndividual;
import com.nsia.officems.gop.decree_individual.domain.DecreeIndividualDTO;
import com.nsia.officems.gop.profile.Profile;

public class DecreeIndividualMapper {
    public static DecreeIndividual map(DecreeIndividualDTO dto, Profile profile, Decree decree,
            DecreeIndividual decreeIndividual) {
        if (dto.getDecreeIndividualNo() != null) {
            decreeIndividual.setDeleted(dto.getDeleted());
            decreeIndividual.setId(decreeIndividual.getId());
        }
        decreeIndividual.setFirstName(dto.getFirstName());
        decreeIndividual.setLastName(dto.getLastName());
        decreeIndividual.setFatherName(dto.getFatherName());
        if (profile != null) {
            decreeIndividual.setProfile(profile);
        }
        decreeIndividual.setDecree(decree);
        return decreeIndividual;
    }

    public static DecreeIndividualDTO mapDecreeIndividual(DecreeIndividual decreeIndividual) {
        DecreeIndividualDTO decreeIndividualDTO = new DecreeIndividualDTO();
        if (decreeIndividual.getProfile() != null) {
            decreeIndividualDTO.setId(decreeIndividual.getProfile().getId());
        }
        decreeIndividualDTO.setDecreeIndividualNo(decreeIndividual.getId());
        decreeIndividualDTO.setFirstName(decreeIndividual.getFirstName());
        decreeIndividualDTO.setLastName(decreeIndividual.getLastName());
        decreeIndividualDTO.setFatherName(decreeIndividual.getFatherName());
        decreeIndividualDTO.setDeleted(decreeIndividual.getDeleted());
        return decreeIndividualDTO;
    }
}