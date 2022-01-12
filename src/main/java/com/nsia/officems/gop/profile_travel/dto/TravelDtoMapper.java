package com.nsia.officems.gop.profile_travel.dto;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems._admin.country.CountryService;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile_job.ProfileJobService;
import com.nsia.officems.gop.profile_travel.Travel;
import com.nsia.officems._admin.province.ProvinceService;

public class TravelDtoMapper {
    public static Travel MapTravelDto(Travel travel, TravelDto dto,
     ProfileService profileService, CountryService countryService, ProvinceService provinceService, ProfileJobService profileJobService){ 
        DateTimeChange changeDate = new DateTimeChange();

        try{
            travel.setActive(true);
            travel.setPurpose(dto.getPurpose() == null? null: dto.getPurpose());
            travel.setDate(dto.getDate() == null? null: changeDate.convertPersianDateToGregorianDate(dto.getDate()));
            travel.setMaktubNo(dto.getMaktubNo() == null? null: dto.getMaktubNo());
            travel.setType(dto.getType() == null? null: dto.getType());
            travel.setProvince(dto.getProvince() == null? null: provinceService.findById(dto.getProvince()));
            travel.setCountry(dto.getCountry() == null? null : countryService.findById(dto.getCountry()));
            travel.setProfile(dto.getProfile() == null? null: profileService.findByIdWithoutRelation(dto.getProfile()));
            travel.setProfileJob(dto.getProfileJob() == null? null: profileJobService.findById(dto.getProfileJob()));
            return travel;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
