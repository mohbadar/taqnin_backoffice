package com.nsia.officems.gop.profile_retirement_type.impl;

import com.nsia.officems.gop.education_level.EducationLevel;
import com.nsia.officems.gop.education_level.EducationLevelRepository;
import com.nsia.officems.gop.education_level.EducationLevelService;
import com.nsia.officems.gop.profile_retirement_type.ProfileRetirementType;
import com.nsia.officems.gop.profile_retirement_type.ProfileRetirementTypeRepository;
import com.nsia.officems.gop.profile_retirement_type.ProfileRetirementTypeService;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileResignationTypeServiceImpl implements ProfileRetirementTypeService{

    @Autowired
    private ProfileRetirementTypeRepository profileResignationTypeRepository;

    @Override
    public List<ProfileRetirementType> findAll() {
        return profileResignationTypeRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public ProfileRetirementType findById(Long id) {
        Optional<ProfileRetirementType> optionalObj = profileResignationTypeRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public ProfileRetirementType create(ProfileRetirementType level) {
        return profileResignationTypeRepository.save(level);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<ProfileRetirementType> level = profileResignationTypeRepository.findById(id);

        if (level.isPresent()) {
            ProfileRetirementType level2 = level.get();
            level2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            level2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            profileResignationTypeRepository.save(level2);
            return true;
        }

        return false;
    }
    
}
