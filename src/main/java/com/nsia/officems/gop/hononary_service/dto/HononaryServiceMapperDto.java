package com.nsia.officems.gop.hononary_service.dto;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.gop.hononary_service.HononaryService;
import com.nsia.officems.gop.profile.ProfileService;
public class HononaryServiceMapperDto {
    public static HononaryService MapHononaryDto(HononaryService hononary, HononaryServiceDto dto,
    ProfileService profileService ){ 
        DateTimeChange changeDate = new DateTimeChange();
       try{
           hononary.setActive(true);
           hononary.setStartDate(dto.getStartDate() == null? null : changeDate.convertPersianDateToGregorianDate(dto.getStartDate()));
           hononary.setEndDate(dto.getEndDate() == null? null: changeDate.convertPersianDateToGregorianDate(dto.getEndDate()));
           hononary.setSource(dto.getSource() == null? null : dto.getSource());
           hononary.setProfile(dto.getProfile() == null? null: profileService.findByIdWithoutRelation(dto.getProfile()));
           return hononary;
       }
       catch(Exception e){
           System.out.println("Exception occured in mapping");
           return null;
       }
   }
}
