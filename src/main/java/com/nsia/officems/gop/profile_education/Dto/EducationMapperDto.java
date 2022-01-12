package com.nsia.officems.gop.profile_education.Dto;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems._admin.country.CountryService;
import com.nsia.officems.gop.education_level.EducationLevelService;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile_education.Education;
import com.nsia.officems.gop.profile_job.ProfileJobService;

public class EducationMapperDto {

    public static Education MapEducationDto(Education education, EducationDto dto,
     ProfileService profileService, CountryService countryService, EducationLevelService educationLevelService, ProfileJobService profileJobService){ 
        DateTimeChange changeDate = new DateTimeChange();

        try{
            education.setActive(true);
            education.setStartDate(dto.getStartDate() == null? null: changeDate.convertPersianDateToGregorianDate(dto.getStartDate()));
            education.setDuration(dto.getDuration() == null? null: dto.getDuration());
            education.setGraduationDate(dto.getGraduationDate() == null? null:changeDate.convertPersianDateToGregorianDate(dto.getGraduationDate()));
            education.setUniversity(dto.getUniversity() == null? null: dto.getUniversity());
            education.setFieldOfStudy(dto.getFieldOfStudy() == null? null: dto.getFieldOfStudy());
            education.setUniversityType(dto.getUniversityType() == null? null: dto.getUniversityType());
            education.setCountry(dto.getCountry() == null? null: countryService.findById(dto.getCountry()));
            education.setLevel(dto.getLevel() == null? null: educationLevelService.findById(dto.getLevel()));
            education.setInsideWork(dto.getInsideWork() == null? null: dto.getInsideWork());
            education.setProfile(dto.getProfile() == null? null: profileService.findByIdWithoutRelation(dto.getProfile()));
            education.setProfileJob(dto.getProfileJob() == null? null: profileJobService.findById(dto.getProfileJob()));
            education.setInsideWork(dto.getInsideWork() == null? null: dto.getInsideWork());

            return education;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
    
}
