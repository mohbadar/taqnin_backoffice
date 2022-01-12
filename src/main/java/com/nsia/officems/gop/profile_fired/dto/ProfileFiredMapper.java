package com.nsia.officems.gop.profile_fired.dto;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems._admin.authority.AuthorityService;
import com.nsia.officems._admin.commission.CommissionService;
import com.nsia.officems.gop.employeePosition.EmployeePositionService;
import com.nsia.officems.gop.employeeStatus.EmployeeStatusService;
import com.nsia.officems._admin.ministry.MinistryService;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile_fired.ProfileFired;
import com.nsia.officems.gop.profile_fired_type.ProfileFiredTypeService;
import com.nsia.officems.gop.profile_job.ProfileJobService;

public class ProfileFiredMapper {
    public static ProfileFired MapFiredDto(ProfileFired fired, ProfileFiredDto dto,
     ProfileService profileService, ProfileJobService profileJobService, ProfileFiredTypeService profileFiredTypeService, EmployeeStatusService employeeStatusService) { 
        DateTimeChange changeDate = new DateTimeChange();

        try{
            fired.setActive(true);
            fired.setMaktubNumber(dto.getMaktubNumber() == null? null: dto.getMaktubNumber());
            fired.setMaktubDate(dto.getMaktubDate() == null? null: changeDate.convertPersianDateToGregorianDate(dto.getMaktubDate()));
            fired.setProfile(dto.getProfile() == null? null: profileService.findByIdWithoutRelation(dto.getProfile()));
            fired.setProfileJob(dto.getProfileJob() == null? null: profileJobService.findById(dto.getProfileJob()));
            fired.setType(dto.getType() == null? null: profileFiredTypeService.findById(dto.getType()));
            fired.setStatus(dto.getStatus() == null? null: employeeStatusService.findById(dto.getStatus()));
            return fired;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
