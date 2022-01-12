package com.nsia.officems.gop.accountability.dto;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.gop.accountability.Accountability;
import com.nsia.officems._admin.authority.AuthorityService;
import com.nsia.officems._admin.commission.CommissionService;
import com.nsia.officems.gop.employeePosition.EmployeePositionService;
import com.nsia.officems.gop.employee_militrary_grade.EmployeeMilitaryGradeService;
import com.nsia.officems._admin.ministry.MinistryService;
import com.nsia.officems.gop.profile.ProfileService;

public class AccountabilityMapper {
    public static Accountability MapAccountablityDto(Accountability accountability, Accountabilitydto dto,
     ProfileService profileService, EmployeePositionService positionService, 
     MinistryService ministryService, AuthorityService authorityService,
      CommissionService commissionService, EmployeeMilitaryGradeService employeeMilitaryGradeService ){
        DateTimeChange changeDate = new DateTimeChange(); 

        try{
            accountability.setActive(true);
            accountability.setStartDate(dto.getStartDate() == null? null: changeDate.convertPersianDateToGregorianDate(dto.getStartDate()));
            accountability.setEndDate(dto.getEndDate() == null? null: changeDate.convertPersianDateToGregorianDate(dto.getEndDate()));
            accountability.setPersonName(dto.getPersonName() == null? null: dto.getPersonName());
            accountability.setPosition(dto.getPosition() == null? null : positionService.findById(dto.getPosition()));
            accountability.setMilitaryPosition(dto.getMilitaryPosition() == null? null: employeeMilitaryGradeService.findById(dto.getMilitaryPosition()));
            accountability.setPositionTitle(dto.getPositionTitle() == null? null : dto.getPositionTitle());
            accountability.setMinistry(dto.getMinistry() == null? null : ministryService.findById(dto.getMinistry()));
            accountability.setAuthority(dto.getAuthority() == null? null : authorityService.findById(dto.getAuthority()));
            accountability.setCommission(dto.getCommission() == null? null : commissionService.findById(dto.getCommission()));
            accountability.setProfile(dto.getProfile() == null? null: profileService.findByIdWithoutRelation(dto.getProfile()));
            return accountability;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping Accountability");
            return null;
        }
    }
    
}
