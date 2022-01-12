package com.nsia.officems.gop.hononary_service.impl;

import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.gop.hononary_service.HononaryService;
import com.nsia.officems.gop.hononary_service.HononaryServiceRepository;
import com.nsia.officems.gop.hononary_service.HononaryServiceS;
import com.nsia.officems.gop.hononary_service.dto.HononaryServiceDto;
import com.nsia.officems.gop.hononary_service.dto.HononaryServiceMapperDto;
import com.nsia.officems.gop.profile.ProfileService;

import java.util.List;
import java.util.Optional;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HononaryServiceSImpl implements HononaryServiceS{
    private ChecklistTitle titles = new ChecklistTitle();

    @Autowired
    private HononaryServiceRepository hononaryServiceRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Autowired
    UserService userService;

    @Override
    public List<HononaryService> findAll() {
        return hononaryServiceRepository.findAll();
    }

    @Override
    public List<HononaryService> findByProfile_id(Long id) {
        return hononaryServiceRepository.findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByEndDateDesc(id);
    }

    @Override
    public HononaryService findById(Long id) {
        Optional<HononaryService> optionalObj = hononaryServiceRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public HononaryService create(HononaryServiceDto dto) {
        HononaryService Newhononary = new HononaryService();
        HononaryService hononary = HononaryServiceMapperDto.MapHononaryDto(Newhononary, dto, profileService);      
        hononary.setCreatedBy(userService.getLoggedInUser().getUsername());
        if (!hononary.equals(null)) {

            profileChecklistService.update(hononary.getProfile().getId(), titles.getHononary_service());
            return hononaryServiceRepository.save(hononary);
        }
        return null;
    }

    public Boolean update(Long id, HononaryServiceDto dto) {
        Optional<HononaryService> objection = hononaryServiceRepository.findById(id);
        if (objection.isPresent()) {
            HononaryService hononary = HononaryServiceMapperDto.MapHononaryDto(objection.get(), dto, profileService);
            hononary.setUpdatedBy(userService.getLoggedInUser().getUsername()); 
            if (!hononary.equals(null)) {
                hononaryServiceRepository.save(hononary);
                return true;
            }
            return false;
        }
        return false;
    }


    @Override
    public Boolean delete(Long id) {
        Optional<HononaryService> hononary = hononaryServiceRepository.findById(id);
        if (hononary.isPresent()) {
            HononaryService hononary2 = hononary.get();
            hononary2.setDeleted(true);
            hononary2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            hononaryServiceRepository.save(hononary2);
            return true;
        }
        return false;
    }
}
