package com.nsia.officems.gop.panelty.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems._admin.authority.AuthorityService;
import com.nsia.officems._admin.commission.CommissionService;
import com.nsia.officems.gop.employeePosition.EmployeePositionService;
import com.nsia.officems.gop.employeeStatus.EmployeeStatusService;
import com.nsia.officems.gop.employee_militrary_grade.EmployeeMilitaryGradeService;
import com.nsia.officems._admin.ministry.MinistryService;
import com.nsia.officems.gop.panelty.Panelty;
import com.nsia.officems.gop.panelty.PaneltyRepository;
import com.nsia.officems.gop.panelty.PaneltyService;
import com.nsia.officems.gop.panelty.dto.PaneltyDto;
import com.nsia.officems.gop.panelty.dto.PaneltyMapperDto;
import com.nsia.officems.gop.paneltyType.PaneltyTypeService;
import com.nsia.officems.gop.paneltyType.paneltyTypeRepository;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile_job.ProfileJob;
import com.nsia.officems.gop.profile_job.ProfileJobRepository;
import com.nsia.officems.gop.profile_job.ProfileJobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaneltyServiceImpl implements PaneltyService {
    private ChecklistTitle titles = new ChecklistTitle();

    @Autowired
    private PaneltyRepository paneltyRepository;

    @Autowired
    private PaneltyTypeService paneltyTypeService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Autowired
    private MinistryService ministryService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private CommissionService commissionService;

    @Autowired
    private EmployeePositionService positionService;

    @Autowired
    private ProfileJobService profileJobService;

    @Autowired
    private EmployeeStatusService employeeStatusService;

    @Autowired
    private EmployeeMilitaryGradeService employeeMilitaryGradeService;

    @Autowired
    private ProfileJobRepository profileJobRepository;

    @Autowired
    UserService userService;

    @Override
    public List<Panelty> findAll() {
        return paneltyRepository.findAll();
    }

    @Override
    public List<Panelty> findByProfile_id(Long id) {
        return paneltyRepository.findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByDecreeDateDesc(id);
    }

    @Override
    public Panelty findById(Long id) {
        Optional<Panelty> optionalObj = paneltyRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Panelty create(PaneltyDto dto) {
        Panelty newPanelty = new Panelty();
        Panelty panelty = PaneltyMapperDto.PaneltyDtoMapper(newPanelty, dto, profileService, paneltyTypeService,
                positionService, ministryService, authorityService, commissionService, profileJobService,
                 employeeStatusService, employeeMilitaryGradeService);
                panelty.setCreatedBy(userService.getLoggedInUser().getUsername());
        if (!panelty.equals(null)) {
            profileChecklistService.update(panelty.getProfile().getId(), titles.getPanelty());
            Panelty paneltyD = paneltyRepository.save(panelty);
            if(paneltyD.getProfileJob() != null){
                ProfileJob job = profileJobService.findById(paneltyD.getProfileJob().getId());
                job.setIsPanelty(true);
                profileJobService.save(job);  
            }

            if(paneltyD.getStatus() != null){
                Profile profile = profileService.findByIdWithoutRelation(paneltyD.getProfile().getId());
                profile.setStatus(paneltyD.getStatus());
                profileService.save(profile);
            }

            if(paneltyD.getProfileJob() != null){
                if(paneltyD.getType() != null){
                    if(paneltyD.getType().getId() == 5){
                        ProfileJob job = profileJobService.findById(paneltyD.getProfileJob().getId());
                        job.setEndDate(paneltyD.getDecreeDate());
                        profileJobService.save(job);
                    }
                }
            }

            if (panelty.getPositionTitle() != null) {
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("panelty", paneltyD);
                profileJobService.create(data);

                if(paneltyD.getProfileJob() != null){
                    ProfileJob job = profileJobService.findById(paneltyD.getProfileJob().getId());
                    Date newDate = DateTimeChange.subtractDays(paneltyD.getDecreeDate(), 1);
                    job.setEndDate(newDate);
                    profileJobService.save(job);
                }
            }
            return paneltyD;
        }
        return null;
    }

    public Boolean update(Long id, PaneltyDto dto) {
        Optional<Panelty> objection = paneltyRepository.findById(id);
        if (objection.isPresent()) {
            Panelty panelty = PaneltyMapperDto.PaneltyDtoMapper(objection.get(), dto, profileService,
                    paneltyTypeService, positionService, ministryService, authorityService, commissionService, profileJobService, 
                    employeeStatusService, employeeMilitaryGradeService);
                    panelty.setUpdatedBy(userService.getLoggedInUser().getUsername());
            if (!panelty.equals(null)) {
                Panelty paneltyD = paneltyRepository.save(panelty);

                if(paneltyD.getProfileJob() != null){
                    if(paneltyD.getType() != null){
                        if(paneltyD.getType().getId() == 5){
                            ProfileJob job = profileJobService.findById(paneltyD.getProfileJob().getId());
                            job.setEndDate(paneltyD.getDecreeDate());
                            profileJobService.save(job);
                        }
                    }
                }

                if(paneltyD.getStatus() != null){
                    Profile profile = profileService.findByIdWithoutRelation(paneltyD.getProfile().getId());
                    profile.setStatus(paneltyD.getStatus());
                    profileService.save(profile);
                }

                if (panelty.getPositionTitle() != null) {
                    Map<String, Object> data = new HashMap<String, Object>();
                    data.put("panelty", paneltyD);
                    this.profileJobService.update(data);

                    if(paneltyD.getProfileJob() != null){
                        ProfileJob job = profileJobService.findById(paneltyD.getProfileJob().getId());
                        Date newDate = DateTimeChange.subtractDays(paneltyD.getDecreeDate(), 1);
                        job.setEndDate(newDate);
                        profileJobService.save(job);
                    }
                }
                return true;
            }
            return false;
        }

        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Panelty> panelty = paneltyRepository.findById(id);

        if (panelty.isPresent()) {
            Panelty panelty2 = panelty.get();
            panelty2.setDeleted(true);
            panelty2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            paneltyRepository.save(panelty2);

            if(panelty2.getProfileJob() != null){
                ProfileJob job = profileJobService.findById(panelty2.getProfileJob().getId());
                job.setIsPanelty(null);
                profileJobService.save(job);  
            }

            if(panelty2.getProfileJob() != null){
                if(panelty2.getType() != null){
                    if(panelty2.getType().getId() == 5){
                        ProfileJob job = profileJobService.findById(panelty2.getProfileJob().getId());
                        job.setEndDate(null);
                        profileJobService.save(job);
                    }
                }
            }

            if (panelty2.getPositionTitle() != null) {
                ProfileJob newJob = profileJobRepository.findByProfile_idAndPanelty_id(panelty2.getProfile().getId(), panelty2.getId());
                newJob.setDeleted(true);
                newJob.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                profileJobRepository.save(newJob);

                if(panelty2.getProfileJob() != null){
                    ProfileJob job = profileJobService.findById(panelty2.getProfileJob().getId());
                    job.setEndDate(null);
                    profileJobService.save(job);
                }
            }

            return true;
        }

        return false;
    }
}
