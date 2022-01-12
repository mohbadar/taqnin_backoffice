package com.nsia.officems.gop.mark_Medal.dto;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.gop.mark_Medal.Medal;
import com.nsia.officems.gop.profile.ProfileService;

public class MedalDtoMapper {
    public static Medal MapPublicationDto(Medal medal, MedalDto dto, ProfileService profileService){ 
        DateTimeChange changeDate = new DateTimeChange();
        try{
            medal.setActive(true);
            medal.setMedalType(dto.getMedalType() == null? null : dto.getMedalType());
            medal.setApprovedSource(dto.getApprovedSource() == null? null: dto.getApprovedSource());
            medal.setMaktubNumber(dto.getMaktubNumber() == null? null : dto.getMaktubNumber());
            medal.setMaktubDate(dto.getMaktubDate() == null? null: changeDate.convertPersianDateToGregorianDate(dto.getMaktubDate()));
            medal.setProfile(dto.getProfile() == null? null: profileService.findByIdWithoutRelation(dto.getProfile()));
            return medal;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
