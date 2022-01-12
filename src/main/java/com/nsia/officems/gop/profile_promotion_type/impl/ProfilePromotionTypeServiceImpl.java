package com.nsia.officems.gop.profile_promotion_type.impl;


import com.nsia.officems.gop.education_level.EducationLevel;
import com.nsia.officems.gop.education_level.EducationLevelRepository;
import com.nsia.officems.gop.education_level.EducationLevelService;
import com.nsia.officems.gop.profile_promotion.ProfilePromotionService;
import com.nsia.officems.gop.profile_promotion_type.ProfilePromotionType;
import com.nsia.officems.gop.profile_promotion_type.ProfilePromotionTypeRepository;
import com.nsia.officems.gop.profile_promotion_type.ProfilePromotionTypeService;
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
public class ProfilePromotionTypeServiceImpl implements ProfilePromotionTypeService{
    @Autowired
    private ProfilePromotionTypeRepository profilePromotionTypeRepository;

    @Override
    public List<ProfilePromotionType> findAll() {
        return profilePromotionTypeRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public ProfilePromotionType findById(Long id) {
        Optional<ProfilePromotionType> optionalObj = profilePromotionTypeRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public ProfilePromotionType create(ProfilePromotionType level) {
        return profilePromotionTypeRepository.save(level);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<ProfilePromotionType> level = profilePromotionTypeRepository.findById(id);

        if (level.isPresent()) {
            ProfilePromotionType level2 = level.get();
            level2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            level2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            profilePromotionTypeRepository.save(level2);
            return true;
        }

        return false;
    }
}
