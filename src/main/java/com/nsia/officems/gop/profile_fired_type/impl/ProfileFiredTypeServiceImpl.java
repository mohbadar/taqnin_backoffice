package com.nsia.officems.gop.profile_fired_type.impl;

import com.nsia.officems.gop.profile_fired_type.ProfileFiredType;
import com.nsia.officems.gop.profile_fired_type.ProfileFiredTypeRepository;
import com.nsia.officems.gop.profile_fired_type.ProfileFiredTypeService;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileFiredTypeServiceImpl implements ProfileFiredTypeService{
    @Autowired
    private ProfileFiredTypeRepository profileFiredRepository;

    @Override
    public List<ProfileFiredType> findAll() {
        return profileFiredRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public ProfileFiredType findById(Long id) {
        Optional<ProfileFiredType> optionalObj = profileFiredRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public ProfileFiredType create(ProfileFiredType level) {
        return profileFiredRepository.save(level);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<ProfileFiredType> level = profileFiredRepository.findById(id);

        if (level.isPresent()) {
            ProfileFiredType level2 = level.get();
            level2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            level2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            profileFiredRepository.save(level2);
            return true;
        }

        return false;
    }
}
