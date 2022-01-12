package com.nsia.officems.gop.profile_retirement.impl;


import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.gop.employeeStatus.EmployeeStatusService;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile_job.ProfileJob;
import com.nsia.officems.gop.profile_job.ProfileJobService;
import com.nsia.officems.gop.profile_retirement.ProfileRetirement;
import com.nsia.officems.gop.profile_retirement.ProfileRetirementRepository;
import com.nsia.officems.gop.profile_retirement.ProfileRetirementService;
import com.nsia.officems.gop.profile_retirement.dto.ProfileResignationDto;
import com.nsia.officems.gop.profile_retirement.dto.ProfileResignationMapper;
import com.nsia.officems.gop.profile_retirement_type.ProfileRetirementTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public class ProfileResignationServiceImpl implements ProfileRetirementService{
    private ChecklistTitle titles = new ChecklistTitle();
    
    @Autowired
    private ProfileRetirementTypeService profileResignationTypeService;

    @Autowired
    private ProfileRetirementRepository profileResignationRepository;

    @Autowired
    private EmployeeStatusService employeeStatusService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Autowired
    private ProfileJobService profileJobService;

    @Autowired
    UserService userService;

    @Override
    public List<ProfileRetirement> findAll() {
        return profileResignationRepository.findAll();
    }

    @Override
    public List<ProfileRetirement> findByProfile_id(Long id) {
        return profileResignationRepository.findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByType_idDesc(id);
    }

    @Override
    public ProfileRetirement findById(Long id) {
        Optional<ProfileRetirement> optionalObj = profileResignationRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public ProfileRetirement create(ProfileResignationDto dto) {
        ProfileRetirement newResignation= new ProfileRetirement();
        ProfileRetirement resignation= ProfileResignationMapper.MapResinationDto(newResignation, dto, profileService, profileResignationTypeService, employeeStatusService, profileJobService);
        resignation.setCreatedBy(userService.getLoggedInUser().getUsername());
        if (!resignation.equals(null)) {
            profileChecklistService.update(resignation.getProfile().getId(), titles.getResignation());

            if(dto.getStatus() != null){
                Profile profile = profileService.findByIdWithoutRelation(resignation.getProfile().getId());
                profile.setStatus(employeeStatusService.findById(dto.getStatus()));
                profileService.save(profile);
            }

            if(resignation.getProfileJob() != null){
                ProfileJob job = profileJobService.findById(resignation.getProfileJob().getId());
                job.setEndDate(resignation.getDecreeDate()== null?null: resignation.getDecreeDate());
                profileJobService.save(job);
            }
            
            return profileResignationRepository.save(resignation);
            
        }
        return null;
    }

    public Boolean update(Long id, ProfileResignationDto dto) {
        Optional<ProfileRetirement> objection = profileResignationRepository.findById(id);
        if (objection.isPresent()) {
            ProfileRetirement resignation= ProfileResignationMapper.MapResinationDto(objection.get(), dto, profileService, profileResignationTypeService, employeeStatusService, profileJobService);
            resignation.setUpdatedBy(userService.getLoggedInUser().getUsername());
            if(!resignation.equals(null))
            {
                if(resignation.getStatus() != null){
                    Profile profile = profileService.findByIdWithoutRelation(resignation.getProfile().getId());
                    profile.setStatus(employeeStatusService.findById(dto.getStatus()));
                    profileService.save(profile);
                }

                if(resignation.getProfileJob() != null){
                    ProfileJob job = profileJobService.findById(resignation.getProfileJob().getId());
                    job.setEndDate(resignation.getDecreeDate()== null?null: resignation.getDecreeDate());
                    profileJobService.save(job);
                }

                profileResignationRepository.save(resignation); 
                return true;
            }
        }

        return false;
    }

    @Override
    public Boolean delete(Long id) {
        long sid = 1;
        Optional<ProfileRetirement> resignation = profileResignationRepository.findById(id);

        if (resignation.isPresent()) {
            ProfileRetirement resignation2 = resignation.get();
            resignation2.setDeleted(true);
            resignation2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

            if(resignation2.getProfileJob() != null){
                ProfileJob job = profileJobService.findById(resignation2.getProfileJob().getId());
                job.setEndDate(null);
                profileJobService.save(job);
            }

            if(resignation2.getStatus() != null){
                Profile profile = profileService.findByIdWithoutRelation(resignation2.getProfile().getId());
                profile.setStatus(employeeStatusService.findById(sid));
                profileService.save(profile);
            }

            profileResignationRepository.save(resignation2);
            return true;
        }

        return false;
    }
    
}
