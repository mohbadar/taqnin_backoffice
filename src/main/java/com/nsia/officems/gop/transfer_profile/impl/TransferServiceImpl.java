package com.nsia.officems.gop.transfer_profile.impl;

import com.nsia.officems.gop.transfer_profile.Transfer;
import com.nsia.officems.gop.transfer_profile.TransferRepository;
import com.nsia.officems.gop.transfer_profile.TransferService;
import com.nsia.officems.gop.transfer_profile.dto.TransferDto;
import com.nsia.officems.gop.transfer_profile.dto.TransferMapperDto;

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
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile.ProfileRepository;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile_job.ProfileJob;
import com.nsia.officems.gop.profile_job.ProfileJobRepository;
import com.nsia.officems.gop.profile_job.ProfileJobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl implements TransferService{
    private ChecklistTitle titles = new ChecklistTitle();

    @Autowired
    private TransferRepository transferRepository;

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
    private ProfileService profileService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeStatusService employeePositionService;

    @Autowired
    private EmployeeMilitaryGradeService employeeMilitaryGradeService;

    @Autowired
    private ProfileJobRepository profileJobRepository;

    @Override
    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }

    @Override
    public List<Transfer> findByProfile_id(Long id) {
        return transferRepository.findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByMaktubDateDesc(id);
    }

    @Override
    public Transfer findById(Long id) {
        Optional<Transfer> optionalObj = transferRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Transfer create(TransferDto dto) {
        Transfer newTransfer = new Transfer();
        Transfer transfer = TransferMapperDto.MapTransferDto(newTransfer, dto, profileService, positionService, ministryService, 
        authorityService, commissionService,profileJobService, employeeMilitaryGradeService);
        transfer.setCreatedBy(userService.getLoggedInUser().getUsername());
        if (!transfer.equals(null)) {
            profileChecklistService.update(transfer.getProfile().getId(), titles.getTransfer());
            Transfer transferD =  transferRepository.save(transfer);

            if(transferD.getProfileJob() != null){
                ProfileJob newJob = profileJobService.findById(transferD.getProfileJob().getId());
                Date newDate = DateTimeChange.subtractDays(transferD.getMaktubDate(), 1);
                newJob.setEndDate(newDate);
                profileJobService.save(newJob);
            }

            if(dto.getStatus() != null){
                Profile profile = profileService.findByIdWithoutRelation(transferD.getProfile().getId());
                profile.setStatus(employeePositionService.findById(dto.getStatus()));
                profileService.save(profile);
            }

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("transfer", transferD);
            this.profileJobService.create(data);
            return transferD;
        }
        return null;
    }

    public Boolean update(Long id, TransferDto dto) {
        Optional<Transfer> objection = transferRepository.findById(id);
        if (objection.isPresent()) {
            Transfer transfer = TransferMapperDto.MapTransferDto(objection.get(), dto, profileService, positionService, ministryService, authorityService,
             commissionService, profileJobService, employeeMilitaryGradeService);
            transfer.setUpdatedBy(userService.getLoggedInUser().getUsername());
            if (!transfer.equals(null)) {
                Transfer transferD =  transferRepository.save(transfer);

                if(transferD.getProfileJob() != null){
                    ProfileJob newJob = profileJobService.findById(transfer.getProfileJob().getId());
                    Date newDate = DateTimeChange.subtractDays(transfer.getMaktubDate(), 1);
                    newJob.setEndDate(newDate);
                    profileJobService.save(newJob);
                }
                if(dto.getStatus() != null){
                    Profile profile = profileService.findByIdWithoutRelation(transferD.getProfile().getId());
                    profile.setStatus(employeePositionService.findById(dto.getStatus()));
                    profileService.save(profile);
                }

                Map<String, Object> data = new HashMap<String, Object>();
                data.put("transfer", transferD);
                this.profileJobService.update(data);
                 return true;
            }
            return false;
        }

        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Transfer> objection = transferRepository.findById(id);

        if (objection.isPresent()) {
            Transfer transfer = objection.get();
            transfer.setDeleted(true);
            transfer.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            transferRepository.save(transfer);

            if(transfer.getProfileJob() != null){
                ProfileJob newJob = profileJobService.findById(transfer.getProfileJob().getId());
                newJob.setEndDate(null);
                profileJobService.save(newJob);
            }

            ProfileJob newJob = profileJobRepository.findByProfile_idAndTransfer_id(transfer.getProfile().getId(), transfer.getId());
            newJob.setDeleted(true);
            newJob.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            profileJobRepository.save(newJob);

            return true;
        }

        return false;
    }
}
