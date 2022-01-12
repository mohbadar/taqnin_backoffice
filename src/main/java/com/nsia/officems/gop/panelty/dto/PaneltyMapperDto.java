package com.nsia.officems.gop.panelty.dto;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems._admin.authority.AuthorityService;
import com.nsia.officems._admin.commission.CommissionService;
import com.nsia.officems.gop.employeePosition.EmployeePositionService;
import com.nsia.officems.gop.employeeStatus.EmployeeStatusService;
import com.nsia.officems.gop.employee_militrary_grade.EmployeeMilitaryGradeService;
import com.nsia.officems._admin.ministry.MinistryService;
import com.nsia.officems.gop.panelty.Panelty;
import com.nsia.officems.gop.paneltyType.PaneltyTypeService;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile_job.ProfileJobService;

public class PaneltyMapperDto {
    public static Panelty PaneltyDtoMapper(Panelty panelty, PaneltyDto dto,
     ProfileService profileService, PaneltyTypeService paneltyTypeService, EmployeePositionService positionService, 
     MinistryService ministryService, AuthorityService authorityService, CommissionService commissionService, ProfileJobService profileJobService, 
     EmployeeStatusService employeeStatusService, EmployeeMilitaryGradeService employeeMilitaryGradeService) { 
        DateTimeChange changeDate = new DateTimeChange();

        try{
            panelty.setActive(true);
            panelty.setType(dto.getType() == null? null: paneltyTypeService.findById(dto.getType()));
            panelty.setProfileJob(dto.getProfileJob() == null? null:profileJobService.findById(dto.getProfileJob()));
            panelty.setDecreeDate(dto.getDecreeDate() == null? null: changeDate.convertPersianDateToGregorianDate(dto.getDecreeDate()));
            panelty.setDegreeSource(dto.getDegreeSource() == null? null: dto.getDegreeSource());
            panelty.setDecreeNumber(dto.getDecreeNumber() == null? null: dto.getDecreeNumber());
            panelty.setSuggestedSource(dto.getSuggestedSource() == null? null: dto.getSuggestedSource());
            panelty.setSuggestedDate(dto.getSuggestedDate() == null? null: changeDate.convertPersianDateToGregorianDate(dto.getSuggestedDate()));
            panelty.setSuggestedNumber(dto.getSuggestedNumber() == null? null: dto.getSuggestedNumber());
            panelty.setProfile(dto.getProfile() == null? null: profileService.findByIdWithoutRelation(dto.getProfile()));
            panelty.setPosition(dto.getPosition() == null? null : positionService.findById(dto.getPosition()));
            panelty.setPositionTitle(dto.getPositionTitle() == null? null : dto.getPositionTitle());
            panelty.setMinistry(dto.getMinistry() == null? null : ministryService.findById(dto.getMinistry()));
            panelty.setStatus(dto.getStatus() == null? null: employeeStatusService.findById(dto.getStatus()));
            panelty.setAuthority(dto.getAuthority() == null? null : authorityService.findById(dto.getAuthority()));
            panelty.setCommission(dto.getCommission() == null? null : commissionService.findById(dto.getCommission()));
            panelty.setMilitaryPosition(dto.getMilitaryPosition() == null? null: employeeMilitaryGradeService.findById(dto.getMilitaryPosition()));
            return panelty;
        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
