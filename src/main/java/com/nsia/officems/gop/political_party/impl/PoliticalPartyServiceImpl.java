package com.nsia.officems.gop.political_party.impl;

import com.nsia.officems.gop.personal_crime.PersonalCrime;
import com.nsia.officems.gop.personal_crime.PersonalCrimeRepository;
import com.nsia.officems.gop.personal_crime.PersonalCrimeService;
import com.nsia.officems.gop.political_party.PoliticalParty;
import com.nsia.officems.gop.political_party.PoliticalPartyRepository;
import com.nsia.officems.gop.political_party.PoliticalPartyService;
import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import java.util.List;
import java.util.Optional;
import java.time.ZoneId;
import java.util.Date;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile_job.ProfileJobService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoliticalPartyServiceImpl implements PoliticalPartyService {
    private ChecklistTitle titles = new ChecklistTitle();
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private PoliticalPartyRepository politicalPartyRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Autowired
    private UserService userService;

    @Override
    public List<PoliticalParty> findAll() {
        return politicalPartyRepository.findAll();
    }

    @Override
    public List<PoliticalParty> findByProfile_id(Long id) {
        return politicalPartyRepository.findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByIdDesc(id);
    }

    @Override
    public PoliticalParty findById(Long id) {
        Optional<PoliticalParty> optionalObj = politicalPartyRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public PoliticalParty create(String data) {
        try {
            JsonNode root = mapper.readTree(data);
            PoliticalParty party = PoliticalParty.builder()
                    .profile(profileService.findByIdWithoutRelation(root.get("profile").asLong()))
                    .type(root.get("type").asText()).name(root.get("name").asText()).build();
            party.setCreatedBy(userService.getLoggedInUser().getUsername());
            if (!party.equals(null)) {

                profileChecklistService.update(party.getProfile().getId(), titles.getPolitical_party());
                return politicalPartyRepository.save(party);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public Boolean update(Long id, String data) {
        try {
            Optional<PoliticalParty> objection = politicalPartyRepository.findById(id);
            if (objection.isPresent()) {
                JsonNode root = mapper.readTree(data);
                PoliticalParty party = objection.get();
                party.setName(root.get("name").asText());
                party.setType(root.get("type").asText());
                party.setUpdatedBy(userService.getLoggedInUser().getUsername());
                if (!party.equals(null)) {
                    politicalPartyRepository.save(party);
                    return true;
                }
                return false;
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean delete(Long id) {
        Optional<PoliticalParty> party = politicalPartyRepository.findById(id);

        if (party.isPresent()) {
            PoliticalParty politicalParty = party.get();
            politicalParty.setDeleted(true);
            politicalParty.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            politicalPartyRepository.save(politicalParty);
            return true;
        }

        return false;
    }

    @Override
    public Boolean typeExists(String type) {
        try {
            PoliticalParty politicalPartyType = politicalPartyRepository.findFirstByType(type);
            if (politicalPartyType != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
