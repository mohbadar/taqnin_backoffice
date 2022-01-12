package com.nsia.officems.gop.personal_crime.impl;

import com.nsia.officems.gop.personal_crime.PersonalCrime;
import com.nsia.officems.gop.personal_crime.PersonalCrimeRepository;
import com.nsia.officems.gop.personal_crime.PersonalCrimeService;


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
public class PersonalCrimeServiceImpl implements PersonalCrimeService{
    private ChecklistTitle titles = new ChecklistTitle();
    ObjectMapper mapper = new ObjectMapper();
    DateTimeChange changeDate = new DateTimeChange();

    @Autowired
    private PersonalCrimeRepository personalCrimeRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Autowired
    private ProfileJobService profileJobService;

    @Autowired
    private UserService userService;

    @Override
    public List<PersonalCrime> findAll() {
        return personalCrimeRepository.findAll();
    }

    @Override
    public List<PersonalCrime> findByProfile_id(Long id) {
        return personalCrimeRepository.findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByDateDesc(id);
    }

    @Override
    public PersonalCrime findById(Long id) {
        Optional<PersonalCrime> optionalObj = personalCrimeRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    
    @Override
    public PersonalCrime create(String data) {
        try {
            JsonNode root = mapper.readTree(data);
            PersonalCrime crime = PersonalCrime.builder()
                    .profile(profileService.findByIdWithoutRelation(root.get("profile").asLong()))
                    .date((root.get("date"))== null? null: changeDate.convertPersianDateToGregorianDate(root.get("date").asText()))
                    .profileJob((root.get("profileJob")) == null? null: profileJobService.findById(root.get("profileJob").asLong()))
                    .crimeType(root.get("crimeType").asText())
                    .build();
                    crime.setCreatedBy(userService.getLoggedInUser().getUsername());
            if (!crime.equals(null)) {

                profileChecklistService.update(crime.getProfile().getId(), titles.getOff_duty_crime());
                return personalCrimeRepository.save(crime);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public Boolean update(Long id, String data) {
        try {
            Optional<PersonalCrime> objection = personalCrimeRepository.findById(id);
            if (objection.isPresent()) {
                JsonNode root = mapper.readTree(data);
                PersonalCrime crime = objection.get();
                crime.setDate((root.get("date"))== null? null: changeDate.convertPersianDateToGregorianDate(root.get("date").asText()));
                crime.setCrimeType(root.get("crimeType").asText());
                crime.setProfileJob((root.get("profileJob")) == null? null: profileJobService.findById(root.get("profileJob").asLong()));
                crime.setUpdatedBy(userService.getLoggedInUser().getUsername());
                if (!crime.equals(null)) {
                    personalCrimeRepository.save(crime);
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
        Optional<PersonalCrime> crime = personalCrimeRepository.findById(id);

        if (crime.isPresent()) {
            PersonalCrime personalCrime =  crime.get();
            personalCrime.setDeleted(true);
            personalCrime.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            personalCrimeRepository.save(personalCrime);
            return true;
        }

        return false;
    }
}
