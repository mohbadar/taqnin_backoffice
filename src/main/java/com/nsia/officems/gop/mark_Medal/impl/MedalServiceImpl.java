package com.nsia.officems.gop.mark_Medal.impl;

import com.nsia.officems.gop.mark_Medal.Medal;
import com.nsia.officems.gop.mark_Medal.MedalRepository;
import com.nsia.officems.gop.mark_Medal.MedalService;
import com.nsia.officems.gop.mark_Medal.dto.MedalDto;
import com.nsia.officems.gop.mark_Medal.dto.MedalDtoMapper;

import java.util.List;
import java.util.Optional;
import java.time.ZoneId;
import java.util.Date;
import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.gop.profile.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedalServiceImpl implements MedalService{

    private ChecklistTitle titles = new ChecklistTitle();

    @Autowired
    private MedalRepository medalRepository;


    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Autowired
    UserService userService;

    @Override
    public List<Medal> findAll() {
        return medalRepository.findAll();
    }

    @Override
    public List<Medal> findByProfile_id(Long id) {
        return medalRepository.findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByIdDesc(id);
    }

    @Override
    public Medal findById(Long id) {
        Optional<Medal> optionalObj = medalRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Medal create(MedalDto dto) {
        Medal newMedal = new Medal();
        Medal medal = MedalDtoMapper.MapPublicationDto(newMedal, dto, profileService);
        medal.setCreatedBy(userService.getLoggedInUser().getUsername());
        if (!medal.equals(null)) {

            profileChecklistService.update(medal.getProfile().getId(), titles.getMedal());
            return medalRepository.save(medal);
        }
        return null;
    }

    public Boolean update(Long id, MedalDto dto) {
        Optional<Medal> objection = medalRepository.findById(id);
        if (objection.isPresent()) {
            Medal medal = MedalDtoMapper.MapPublicationDto(objection.get(), dto, profileService);
            medal.setUpdatedBy(userService.getLoggedInUser().getUsername());
            if (!medal.equals(null)) {
                medalRepository.save(medal);
                return true;
            }
            return false;
        }

        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Medal> medal = medalRepository.findById(id);
        if (medal.isPresent()) {
            Medal medal2 = medal.get();
            medal2.setDeleted(true);
            medal2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            medalRepository.save(medal2);
            return true;
        }

        return false;
    }
    
}
