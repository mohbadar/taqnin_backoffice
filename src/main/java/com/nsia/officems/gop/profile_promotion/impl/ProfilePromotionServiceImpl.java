package com.nsia.officems.gop.profile_promotion.impl;

import com.nsia.officems.gop.profile_promotion.ProfilePromotion;
import com.nsia.officems.gop.profile_promotion.ProfilePromotionRepository;
import com.nsia.officems.gop.profile_promotion.ProfilePromotionService;
import com.nsia.officems.gop.profile_promotion.dto.ProfilePromotionDto;
import com.nsia.officems.gop.profile_promotion.dto.ProfilePromotionMapper;
import com.nsia.officems.gop.profile_promotion_type.ProfilePromotionTypeService;

import java.util.List;
import java.util.Optional;
import java.time.ZoneId;
import java.util.Date;
import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.gop.employeeGrade.EmployeeGradeService;
import com.nsia.officems.gop.employee_militrary_grade.EmployeeMilitaryGradeService;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile_job.ProfileJobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfilePromotionServiceImpl implements ProfilePromotionService{
    private ChecklistTitle titles = new ChecklistTitle();

    @Autowired
    private ProfilePromotionRepository profilePromotionRepository;

    @Autowired
    private EmployeeGradeService gradeService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Autowired
    private UserService userService;

    @Autowired 
    private ProfileJobService profileJobService;

    @Autowired
    private ProfilePromotionTypeService profilePromotionTypeService;

    @Autowired
    private EmployeeMilitaryGradeService employeeMilitaryGradeService;

    @Override
    public List<ProfilePromotion> findAll() {
        return profilePromotionRepository.findAll();
    }

    @Override
    public List<ProfilePromotion> findByProfile_id(Long id) {
        return profilePromotionRepository.findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByMaktubDateDesc(id);
    }

    @Override
    public ProfilePromotion findbyLatestPromotion(Long id){

        return profilePromotionRepository.findbyLatestPromotion(id);
    }

    @Override
    public ProfilePromotion findById(Long id) {
        Optional<ProfilePromotion> optionalObj = profilePromotionRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public ProfilePromotion create(ProfilePromotionDto dto) {
        ProfilePromotion newPromotion = new ProfilePromotion();
        ProfilePromotion promotion = ProfilePromotionMapper.MapPromotionDto(newPromotion, dto, profileService, gradeService, profileJobService, 
        profilePromotionTypeService, employeeMilitaryGradeService);
        promotion.setCreatedBy(userService.getLoggedInUser().getUsername());
        if (!promotion.equals(null)) {
            profileChecklistService.update(promotion.getProfile().getId(), titles.getPromotion());
            return profilePromotionRepository.save(promotion);
        }
        return null;
    }

    public Boolean update(Long id, ProfilePromotionDto dto) {
        Optional<ProfilePromotion> objection = profilePromotionRepository.findById(id);
        if (objection.isPresent()) {
            ProfilePromotion promotion = ProfilePromotionMapper.MapPromotionDto(objection.get(), dto, profileService, gradeService, profileJobService, 
            profilePromotionTypeService, employeeMilitaryGradeService);
            promotion.setUpdatedBy(userService.getLoggedInUser().getUsername());
            if (!promotion.equals(null)) {
                profilePromotionRepository.save(promotion);
                return true;
            }
            return false;
        }

        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<ProfilePromotion> objection = profilePromotionRepository.findById(id);

        if (objection.isPresent()) {
            ProfilePromotion promotion = objection.get();
            promotion.setDeleted(true);
            promotion.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            profilePromotionRepository.save(promotion);
            return true;
        }

        return false;
    }
}
