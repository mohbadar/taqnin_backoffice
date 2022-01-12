package com.nsia.officems.gop.military_service.impl;

import com.nsia.officems.gop.military_service.MilitaryService;
import com.nsia.officems.gop.military_service.MilitaryServiceRepository;
import com.nsia.officems.gop.military_service.MilitaryServiceS;

import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import java.util.List;
import java.util.Optional;
import java.time.ZoneId;
import java.util.Date;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.gop.profile.ProfileService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MilitaryServiceImpl implements MilitaryServiceS {
    private ChecklistTitle titles = new ChecklistTitle();
    DateTimeChange changetime = new DateTimeChange();
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MilitaryServiceRepository militaryServiceRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Autowired
    private UserService userService;

    @Override
    public List<MilitaryService> findAll() {
        return militaryServiceRepository.findAll();
    }

    @Override
    public List<MilitaryService> findByProfile_id(Long id) {
        return militaryServiceRepository.findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByIdDesc(id);
    }

    @Override
    public MilitaryService findById(Long id) {
        Optional<MilitaryService> optionalObj = militaryServiceRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public MilitaryService create(String data) {
        try {
            JsonNode root = mapper.readTree(data);
            MilitaryService military = MilitaryService.builder()
                    .profile(profileService.findByIdWithoutRelation(root.get("profile").asLong()))
                    .startDate(changetime.convertPersianDateToGregorianDate(root.get("startDate").asText()))
                    .endDate(changetime.convertPersianDateToGregorianDate(root.get("endDate").asText()))
                    .type(root.get("type").asText())
                    .detail(root.get("detail").asText() == null ? null : root.get("detail").asText()).build();
            military.setCreatedBy(userService.getLoggedInUser().getUsername());
            if (!military.equals(null)) {

                profileChecklistService.update(military.getProfile().getId(), titles.getMilitary());
                return militaryServiceRepository.save(military);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public Boolean update(Long id, String data) {
        try {
            Optional<MilitaryService> objection = militaryServiceRepository.findById(id);
            if (objection.isPresent()) {
                JsonNode root = mapper.readTree(data);
                MilitaryService military = objection.get();
                military.setStartDate(changetime.convertPersianDateToGregorianDate(root.get("startDate").asText()));
                military.setEndDate(changetime.convertPersianDateToGregorianDate(root.get("endDate").asText()));
                military.setType(root.get("type").asText());
                military.setDetail(root.get("detail").asText() == null ? null : root.get("detail").asText());
                military.setUpdatedBy(userService.getLoggedInUser().getUsername());

                if (!military.equals(null)) {
                    militaryServiceRepository.save(military);
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
        Optional<MilitaryService> military = militaryServiceRepository.findById(id);

        if (military.isPresent()) {
            MilitaryService militaryService = military.get();
            militaryService.setDeleted(true);
            militaryService.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            militaryServiceRepository.save(militaryService);
            return true;
        }

        return false;
    }

    @Override
    public Boolean typeExists(String type) {
        try {
            MilitaryService militaryServiceType = militaryServiceRepository.findFirstByType(type);
            if (militaryServiceType != null) {
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
