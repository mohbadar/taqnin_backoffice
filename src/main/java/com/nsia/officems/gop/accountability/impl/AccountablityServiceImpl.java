package com.nsia.officems.gop.accountability.impl;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.gop.accountability.Accountability;
import com.nsia.officems.gop.accountability.AccountablityRepository;
import com.nsia.officems.gop.accountability.AccountablityService;
import com.nsia.officems.gop.accountability.dto.AccountabilityMapper;
import com.nsia.officems.gop.accountability.dto.Accountabilitydto;
import com.nsia.officems._admin.authority.AuthorityService;
import com.nsia.officems._admin.commission.CommissionService;
import com.nsia.officems.gop.employeePosition.EmployeePositionService;
import com.nsia.officems.gop.employee_militrary_grade.EmployeeMilitaryGradeService;
import com.nsia.officems._admin.ministry.MinistryService;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile_job.ProfileJob;
import com.nsia.officems.gop.profile_job.ProfileJobRepository;
import com.nsia.officems.gop.profile_job.ProfileJobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountablityServiceImpl implements AccountablityService{
    private ChecklistTitle titles = new ChecklistTitle();

    @Autowired
    private AccountablityRepository accountablityRepository;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Autowired
    private MinistryService ministryService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private CommissionService commissionService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private EmployeePositionService positionService;

    @Autowired
    private ProfileJobService profileJobService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeMilitaryGradeService employeeMilitaryGradeService;

    @Autowired
    private ProfileJobRepository profileJobRepository;

    @Override
    public List<Accountability> findAll() {
        return accountablityRepository.findAll();
    }

    @Override
    public List<Accountability> findByProfile_id(Long id) {
        return accountablityRepository.findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByEndDateDesc(id);
    }

    @Override
    public Accountability findById(Long id) {
        Optional<Accountability> optionalObj = accountablityRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Accountability create(Accountabilitydto dto) {
        Accountability newAccountability = new Accountability();
        Accountability accountability = AccountabilityMapper.MapAccountablityDto(newAccountability, dto, profileService, positionService, ministryService, 
        authorityService, commissionService, employeeMilitaryGradeService);
        accountability.setCreatedBy(userService.getLoggedInUser().getUsername());
        if (!accountability.equals(null)) {
            profileChecklistService.update(accountability.getProfile().getId(), titles.getAccountability());
            Accountability accountabilityD = accountablityRepository.save(accountability);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("accountability", accountabilityD);
            try{
                this.profileJobService.create(data);
                return accountabilityD;
            } 
            catch(Exception e){
                System.out.println("exception in Accountability ProfileJobService ");
                return null;
            }  
        }
        return null;
    }

    public Boolean update(Long id, Accountabilitydto dto) {
        Optional<Accountability> objection = accountablityRepository.findById(id);
        if (objection.isPresent()) {
            Accountability accountability = AccountabilityMapper.MapAccountablityDto(objection.get(), dto, profileService, positionService, ministryService, 
            authorityService, commissionService, employeeMilitaryGradeService);
            accountability.setUpdatedBy(userService.getLoggedInUser().getUsername());
            if (!accountability.equals(null)) {
                Accountability accountabilityD = accountablityRepository.save(accountability);
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("accountability", accountabilityD);
                try{
                    this.profileJobService.update(data);
                    return true;
                }
                catch(Exception e){
                    System.out.println("exception in acountability profileJob update: ");
                    return false;
                }
            }
            return false;
        }

        return false;
    }


    @Override
    public Boolean delete(Long id) {
        Optional<Accountability> accountablity = accountablityRepository.findById(id);

        if (accountablity.isPresent()) {
            Accountability accountability2 = accountablity.get();
            accountability2.setDeleted(true);
            accountability2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            accountablityRepository.save(accountability2);

            ProfileJob newJob = profileJobRepository.findByProfile_idAndAccountability_id(accountability2.getProfile().getId(), accountability2.getId());
            newJob.setDeleted(true);
            newJob.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            profileJobRepository.save(newJob);

            return true;
        }

        return false;
    }
}
