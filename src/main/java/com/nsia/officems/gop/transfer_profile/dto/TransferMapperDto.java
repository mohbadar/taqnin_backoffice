package com.nsia.officems.gop.transfer_profile.dto;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems._admin.authority.AuthorityService;
import com.nsia.officems._admin.commission.CommissionService;
import com.nsia.officems.gop.employeePosition.EmployeePositionService;
import com.nsia.officems.gop.employee_militrary_grade.EmployeeMilitaryGradeService;
import com.nsia.officems._admin.ministry.MinistryService;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile_job.ProfileJobService;
import com.nsia.officems.gop.transfer_profile.Transfer;

public class TransferMapperDto {
    public static Transfer MapTransferDto(Transfer transfer, TransferDto dto,
     ProfileService profileService, EmployeePositionService positionService, 
     MinistryService ministryService, AuthorityService authorityService, 
     CommissionService commissionService, ProfileJobService profileJobService, EmployeeMilitaryGradeService employeeMilitaryGradeService){
        DateTimeChange changeDate = new DateTimeChange(); 

        try{
            transfer.setActive(true);
            transfer.setMaktubNumber(dto.getMaktubNumber() == null? null: dto.getMaktubNumber());
            transfer.setMaktubDate(dto.getMaktubDate() == null? null: changeDate.convertPersianDateToGregorianDate(dto.getMaktubDate()));
            transfer.setPosition(dto.getPosition() == null? null : positionService.findById(dto.getPosition()));
            transfer.setMilitaryPosition(dto.getMilitaryPosition() == null? null: employeeMilitaryGradeService.findById(dto.getMilitaryPosition()));
            transfer.setPositionTitle(dto.getPositionTitle() == null? null : dto.getPositionTitle());
            transfer.setMinistry(dto.getMinistry() == null? null : ministryService.findById(dto.getMinistry()));
            transfer.setAuthority(dto.getAuthority() == null? null : authorityService.findById(dto.getAuthority()));
            transfer.setCommission(dto.getCommission() == null? null : commissionService.findById(dto.getCommission()));
            transfer.setProfile(dto.getProfile() == null? null: profileService.findByIdWithoutRelation(dto.getProfile()));
            transfer.setProfileJob(dto.getProfileJob() == null? null: profileJobService.findById(dto.getProfileJob()));
            return transfer;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
