package com.nsia.officems.gop.profile_retirement.dto;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.gop.employeeStatus.EmployeeStatusService;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile_job.ProfileJobService;
import com.nsia.officems.gop.profile_retirement.ProfileRetirement;
import com.nsia.officems.gop.profile_retirement_type.ProfileRetirementTypeService;

public class ProfileResignationMapper {
    
    public static ProfileRetirement MapResinationDto(ProfileRetirement resignation, ProfileResignationDto dto,
     ProfileService profileService, ProfileRetirementTypeService profileResignationTypeService,
      EmployeeStatusService employeeStatusService, ProfileJobService profileJobService){ 
        DateTimeChange changeDate = new DateTimeChange();

        try{
            resignation.setActive(true);
            resignation.setMaktubDate(dto.getMaktubDate() == null? null: changeDate.convertPersianDateToGregorianDate(dto.getMaktubDate()));
            resignation.setMaktubNumber(dto.getMaktubNumber() == null? null: dto.getMaktubNumber());
            resignation.setDecreeDate(dto.getDecreeDate() == null? null:changeDate.convertPersianDateToGregorianDate(dto.getDecreeDate()));
            resignation.setDecreeNumber(dto.getDecreeNumber() == null? null: dto.getDecreeNumber());
            resignation.setDetail(dto.getDetail() == null? null: dto.getDetail());
            resignation.setType(dto.getType() == null? null: profileResignationTypeService.findById(dto.getType()));
            resignation.setStatus(dto.getStatus() == null? null: employeeStatusService.findById(dto.getStatus()));
            resignation.setProfile(dto.getProfile() == null? null: profileService.findByIdWithoutRelation(dto.getProfile()));
            resignation.setProfileJob(dto.getProfileJob() == null? null: profileJobService.findById(dto.getProfileJob()));

            return resignation;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
