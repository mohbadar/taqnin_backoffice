package com.nsia.officems.gop.Profile_checklist.impl;

import com.nsia.officems.gop.Profile_checklist.ProfileChecklist;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistRepository;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems.gop.Profile_checklist.dto.ProfileCheckListMapper;
import com.nsia.officems.gop.profile.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProfileChecklistServiceImpl implements ProfileChecklistService {

    @Autowired
    private ProfileChecklistRepository profileChecklistRepository;

    @Autowired
    private ProfileService profileService;

    @Override
    public List<ProfileChecklist> findAll() {
        return profileChecklistRepository.findAll();
    }

    @Override
    public List<ProfileChecklist> findByProfile_id(Long id) {
        return profileChecklistRepository.findByProfile_id(id);
    }

    @Override
    public ProfileChecklist findById(Long id) {
        Optional<ProfileChecklist> checklist = profileChecklistRepository.findById(id);
        if (checklist.isPresent())
            return checklist.get();
        return null;
    }

    @Override
    public Boolean create(Long profileId) {
        List<ProfileChecklist> checklist = ProfileCheckListMapper.ProfileCheckListMappList(profileId, profileService);
        if (!checklist.equals(null)) {
            for (ProfileChecklist profileChecklist : checklist) {
                profileChecklistRepository.save(profileChecklist);
            }
            return true;
        }
        return false;
    }

    public Boolean update(Long profileId, String title) {
        ProfileChecklist checklist = profileChecklistRepository.findByProfile_idAndTitle(profileId, title);
        if (!checklist.equals(null)) {
            checklist.setStatus(true);
            profileChecklistRepository.save(checklist);
            return true;
        }

        return false;
    }
    

    @Override
    public Boolean updateprofileSetting(Long profileId, String title, Boolean status){
        ProfileChecklist checklist = profileChecklistRepository.findByProfile_idAndTitle(profileId, title);
        if (!checklist.equals(null)) {
            checklist.setStatus(status);
            profileChecklistRepository.save(checklist);
            return true;
        }

        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<ProfileChecklist> checklist = profileChecklistRepository.findById(id);

        if (checklist.isPresent()) {
            ProfileChecklist checklist2 = checklist.get();
            checklist2.setDeleted(true);
            checklist2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            profileChecklistRepository.save(checklist2);
            return true;
        }

        return false;
    }

}
