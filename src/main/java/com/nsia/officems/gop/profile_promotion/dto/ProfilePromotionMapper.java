package com.nsia.officems.gop.profile_promotion.dto;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.gop.employeeGrade.EmployeeGradeService;
import com.nsia.officems.gop.employee_militrary_grade.EmployeeMilitaryGradeService;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile_job.ProfileJobService;
import com.nsia.officems.gop.profile_promotion.ProfilePromotion;
import com.nsia.officems.gop.profile_promotion_type.ProfilePromotionTypeService;

public class ProfilePromotionMapper {
    public static ProfilePromotion MapPromotionDto(ProfilePromotion promotion, ProfilePromotionDto dto,
     ProfileService profileService, EmployeeGradeService gradeService, ProfileJobService profileJobService,
      ProfilePromotionTypeService profilePromotionTypeService, EmployeeMilitaryGradeService employeeMilitaryGradeService){  
        DateTimeChange changeDate = new DateTimeChange();

        try{
            promotion.setActive(true);
            promotion.setIntialJob(false);
            promotion.setMaktubNumber(dto.getMaktubNumber() == null? null: dto.getMaktubNumber());
            promotion.setMaktubDate(dto.getMaktubDate() == null? null: changeDate.convertPersianDateToGregorianDate(dto.getMaktubDate()));
            promotion.setOldGrade(dto.getOldGrade() == null? null : gradeService.findById(dto.getOldGrade()));
            promotion.setNewGrade(dto.getNewGrade() == null? null : gradeService.findById(dto.getNewGrade()));
            promotion.setProfile(dto.getProfile() == null? null: profileService.findByIdWithoutRelation(dto.getProfile()));
            promotion.setProfileJob(dto.getProfileJob() == null? null: profileJobService.findById(dto.getProfileJob()));
            promotion.setQadamDay(dto.getQadamDay() == null? null: dto.getQadamDay());
            promotion.setQadamMonth(dto.getQadamMonth() == null? null: dto.getQadamMonth());
            promotion.setQadamYear(dto.getQadamYear() == null? null: dto.getQadamYear());
            promotion.setType(dto.getType() == null? null: profilePromotionTypeService.findById(dto.getType()));
            promotion.setOldMilitaryGrade(dto.getOldMilitaryGrade() == null? null: employeeMilitaryGradeService.findById(dto.getOldMilitaryGrade()));
            promotion.setNewMilitaryGrade(dto.getNewMilitaryGrade() == null? null: employeeMilitaryGradeService.findById(dto.getNewMilitaryGrade()));
            return promotion;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
