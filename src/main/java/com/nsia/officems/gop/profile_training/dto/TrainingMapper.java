package com.nsia.officems.gop.profile_training.dto;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems._admin.country.CountryService;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile_training.Training;
import com.nsia.officems._admin.province.ProvinceService;

public class TrainingMapper {
    public static Training MapTrainingDto(Training training, TrainingDto dto,
     ProfileService profileService, CountryService countryService, ProvinceService provinceService){ 
        DateTimeChange changeDate = new DateTimeChange();
        try{
            training.setActive(true);
            training.setTitle(dto.getTitle() == null? null: dto.getTitle());
            training.setType(dto.getType() == null? null: dto.getType());
            training.setStartDate(dto.getStartDate() == null? null: changeDate.convertPersianDateToGregorianDate(dto.getStartDate()));
            training.setEndDate(dto.getEndDate() == null? null: changeDate.convertPersianDateToGregorianDate(dto.getEndDate()));
            training.setSeminarType(dto.getSeminarType() == null? null: dto.getSeminarType());
            training.setProvince(dto.getProvince() == null? null: provinceService.findById(dto.getProvince()));
            training.setCountry(dto.getCountry() == null? null : countryService.findById(dto.getCountry()));
            training.setProfile(dto.getProfile() == null? null: profileService.findByIdWithoutRelation(dto.getProfile()));
            return training;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
