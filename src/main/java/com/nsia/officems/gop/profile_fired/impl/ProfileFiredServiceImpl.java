package com.nsia.officems.gop.profile_fired.impl;

import com.nsia.officems.gop.profile_fired.ProfileFired;
import com.nsia.officems.gop.profile_fired.ProfileFiredRepository;
import com.nsia.officems.gop.profile_fired.ProfileFiredService;
import com.nsia.officems.gop.profile_fired.dto.ProfileFiredDto;
import com.nsia.officems.gop.profile_fired.dto.ProfileFiredMapper;
import com.nsia.officems.gop.profile_fired_type.ProfileFiredTypeService;
import com.nsia.officems.gop.profile_job.ProfileJob;
import com.nsia.officems.gop.profile_job.ProfileJobService;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems._admin.authority.AuthorityService;
import com.nsia.officems._admin.commission.CommissionService;
import com.nsia.officems.gop.employeePosition.EmployeePositionService;
import com.nsia.officems.gop.employeeStatus.EmployeeStatusService;
import com.nsia.officems._admin.ministry.MinistryService;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileFiredServiceImpl implements ProfileFiredService {
    private ChecklistTitle titles = new ChecklistTitle();

    @Autowired
    private ProfileFiredRepository profileFiredRepository;

    @Autowired
    private ProfileJobService profileJobService;

    @Autowired
    private EmployeePositionService positionService;

    @Autowired
    private EmployeeStatusService employeeStatusService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    ProfileFiredTypeService profileFiredTypeService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Autowired
    private UserService userService;

    @Override
    public List<ProfileFired> findAll() {
        return profileFiredRepository.findAll();
    }

    @Override
    public List<ProfileFired> findByProfile_id(Long id) {
        return profileFiredRepository.findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByIdDesc(id);
    }

    @Override
    public ProfileFired findById(Long id) {
        Optional<ProfileFired> optionalObj = profileFiredRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public ProfileFired create(ProfileFiredDto dto) {
        ProfileFired newFired = new ProfileFired();
        ProfileFired fired = ProfileFiredMapper.MapFiredDto(newFired, dto, profileService, profileJobService, profileFiredTypeService, employeeStatusService);
        fired.setCreatedBy(userService.getLoggedInUser().getUsername());
        if (!fired.equals(null)) {
            try{
                if(fired.getProfileJob() != null){
                    ProfileJob job = profileJobService.findById(fired.getProfileJob().getId());
                    job.setEndDate(fired.getMaktubDate());
                    profileJobService.save(job);
                }

                if(fired.getStatus() != null){
                    Profile profile = profileService.findByIdWithoutRelation(fired.getProfile().getId());
                    profile.setStatus(fired.getStatus());
                    profileService.save(profile);
                }
                profileChecklistService.update(fired.getProfile().getId(), titles.getFired_from_duty());
                return profileFiredRepository.save(fired);
            }
            catch(Exception e){
                System.out.println("Exception in creating profileFired");
                return null;
            }
        }
        return null;
    }

    public Boolean update(Long id, ProfileFiredDto dto) {
        Optional<ProfileFired> objection = profileFiredRepository.findById(id);
        if (objection.isPresent()) {
            ProfileFired fired = ProfileFiredMapper.MapFiredDto(objection.get(), dto, profileService, profileJobService, profileFiredTypeService, employeeStatusService);
            fired.setUpdatedBy(userService.getLoggedInUser().getUsername());
            if (!fired.equals(null)) {
               try{
                    if(fired.getProfileJob() != null){
                        ProfileJob job = profileJobService.findById(fired.getProfileJob().getId());
                        job.setEndDate(fired.getMaktubDate());
                        profileJobService.save(job);
                    }
                    if(fired.getStatus() != null){
                        Profile profile = profileService.findByIdWithoutRelation(fired.getProfile().getId());
                        profile.setStatus(fired.getStatus());
                        profileService.save(profile);
                    }
                    profileFiredRepository.save(fired);
                    return true;
               }
               catch(Exception e){
                    System.out.println("Exception in updateing profileFired");
                    return null;
               }
            }
            return false;
        }

        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<ProfileFired> objection = profileFiredRepository.findById(id);

        if (objection.isPresent()) {
            ProfileFired fired = objection.get();
            fired.setDeleted(true);
            fired.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            profileFiredRepository.save(fired);

            if(fired.getProfileJob() != null){
                ProfileJob job = profileJobService.findById(fired.getProfileJob().getId());
                job.setEndDate(null);
                profileJobService.save(job);
            }

            return true;
        }

        return false;
    }
}
