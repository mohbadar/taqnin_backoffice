package com.nsia.officems.gop.profile_job.impl;

import java.util.Optional;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.gop.accountability.Accountability;
import com.nsia.officems.gop.employeeStatus.EmployeeStatusService;
import com.nsia.officems.gop.panelty.Panelty;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile.Dto.RevisionDTO;
import com.nsia.officems.gop.profile_job.ProfileJob;
import com.nsia.officems.gop.profile_job.ProfileJobRepository;
import com.nsia.officems.gop.profile_job.ProfileJobService;
import com.nsia.officems.gop.profile_job.dto.JobMapper;
import com.nsia.officems.gop.transfer_profile.Transfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;

@Service
public class ProfileJobServiceImpl implements ProfileJobService {
    ObjectMapper mapper = new ObjectMapper();
    DateTimeChange changeDate = new DateTimeChange();
    
    @Autowired
    private ProfileJobRepository profileJobRepository;

    @Autowired
    private EmployeeStatusService employeeStatusService;


    @Autowired
    private ProfileService profileService;

    @Autowired
    UserService userService;

    @Override
    public Map<String,Object> calculteWorkExperienceByProfile(Long proId){
        Long years, months, days;
        Long number_of_days = profileJobRepository.calculateWorkExperience(proId);
        if(number_of_days != null && number_of_days != 0)
        {
            years = number_of_days / 365;
            months = (number_of_days - (years*365))/ 30;
            days = number_of_days - (years*365) - (months*30);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("years", years);
            data.put("months", months);
            data.put("days", days);
            return data;
        }

        return null;
       
    }

    @Override
    public List<ProfileJob> findByProfile_id(Long id) {
        return profileJobRepository.findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByMaktubDateDesc(id);
    }

    @Override
    public List<ProfileJob> findByProfile_idEndDateIsNull(Long id){
        return profileJobRepository.findByProfile_idAndEndDateIsNullAndDeletedIsFalseOrDeletedIsNull(id);
    }

    @Override
    public ProfileJob findById(Long id) {
        Optional<ProfileJob> optionalObj = profileJobRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public ProfileJob create(Map<String, Object> map) {
        ProfileJob newJob = new ProfileJob();

        if (map.get("profile") != null) {
            Profile profile = (Profile) (map.get("profile").equals(null) ? null : map.get("profile"));
            ProfileJob job = JobMapper.MapProfileObj(newJob, profile);
            if (!job.equals(null)) {
                return profileJobRepository.save(job);
            }
            return null;
        }
        else if (map.get("transfer") != null) {
            Transfer transfer = (Transfer) (map.get("transfer").equals(null) ? null : map.get("transfer"));
            ProfileJob job = JobMapper.MapTransferObj(newJob, transfer, transfer.getProfile());
            if (!job.equals(null)) {
                Profile profile = profileService.findByIdWithoutRelation(job.getProfile().getId());
                profile.setDisable(true);
                return profileJobRepository.save(job);
            }
            return null;
        }

        else if (map.get("panelty") != null) {
            Panelty panelty = (Panelty) (map.get("panelty").equals(null)? null: map.get("panelty"));
            ProfileJob job = JobMapper.MapPaneltyObj(newJob, panelty, panelty.getProfile());
            if (!job.equals(null)) {
                Profile profile = profileService.findByIdWithoutRelation(job.getProfile().getId());
                profile.setDisable(true);
                return profileJobRepository.save(job);
            }
            return null;
        }

        else if (map.get("accountability") != null) {
            Accountability accountability = (Accountability) (map.get("accountability").equals(null)? null: map.get("accountability"));
            ProfileJob job = JobMapper.MapAccountiblityObj(newJob, accountability, accountability.getProfile());
            if (!job.equals(null)) {
                Profile profile = profileService.findByIdWithoutRelation(job.getProfile().getId());
                profile.setDisable(true);
                return profileJobRepository.save(job);
            }
            return null;
        }
        

        // profile.setCreatedBy(userService.getLoggedInUser().getUsername());
        // profile.setEnvSlug(userAuthService.getCurrentEnv());
        return null;
    }

    public Boolean update(Map<String, Object> map) {
        ProfileJob newJob;
        if (map.get("profile") != null) {
            Profile profile = (Profile) (map.get("profile").equals(null) ? null : map.get("profile"));

            if (!profile.equals(null)) {
                newJob = profileJobRepository.findByProfile_idAndIntialJobTrue(profile.getId());
                ProfileJob job = JobMapper.MapProfileObj(newJob, profile);
                if (!job.equals(null)) {
                    profileJobRepository.save(job);
                    return true;
                }

                return false;
            }
            return false;
        }

        else if(map.get("transfer") != null){
            Transfer transfer = (Transfer) (map.get("transfer").equals(null)? null: map.get("transfer"));

            if (!transfer.equals(null)) {
                newJob = profileJobRepository.findByProfile_idAndTransfer_id(transfer.getProfile().getId(),transfer.getId());
                ProfileJob job = JobMapper.MapTransferObj(newJob, transfer, transfer.getProfile());
                if (!job.equals(null)) {
                    profileJobRepository.save(job);
                    return true;
                }
    
                return false;
            }
            
            return false;
        }

        else if(map.get("panelty") != null){
            Panelty panelty = (Panelty) (map.get("panelty").equals(null)? null: map.get("panelty"));
            if (!panelty.equals(null)) {
                newJob = profileJobRepository.findByProfile_idAndPanelty_id(panelty.getProfile().getId(), panelty.getId());
                ProfileJob job = JobMapper.MapPaneltyObj(newJob, panelty, panelty.getProfile());
                if (!job.equals(null)) {
                    profileJobRepository.save(job);
                    return true;
                }
                return false;
            }
            
            return false;
        }

        else if(map.get("accountability") != null){
            Accountability accountability = (Accountability) (map.get("accountability").equals(null)? null: map.get("accountability"));
            if (!accountability.equals(null)) {
                newJob = profileJobRepository.findByProfile_idAndAccountability_id(accountability.getProfile().getId(), accountability.getId());
                ProfileJob job = JobMapper.MapAccountiblityObj(newJob, accountability, accountability.getProfile());
                if (!job.equals(null)) {
                    profileJobRepository.save(job);
                    return true;
                }
                return false;
            }
            
            return false;
        }

        return false;
    }

    public Boolean save(ProfileJob job){
        if(!job.equals(null)){
            profileJobRepository.save(job);
            return true;
        }

        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<ProfileJob> objection = profileJobRepository.findById(id);

        if (objection.isPresent()) {
            ProfileJob job = objection.get();
            job.setDeleted(true);
            job.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            profileJobRepository.save(job);
            return true;
        }

        return false;
    }

    public ProfileJob createBreakJob(String data){
        try {
            JsonNode root = mapper.readTree(data);
            ProfileJob job = ProfileJob.builder()
                    .profile(profileService.findByIdWithoutRelation(root.get("profile").asLong()))
                    .maktubDate(changeDate.convertPersianDateToGregorianDate(root.get("maktubDate").asText()))
                    .endDate(changeDate.convertPersianDateToGregorianDate(root.get("endDate").asText()))
                    .breakDetail(root.get("breakDetail").asText())
                    .status(root.get("status") == null? null: employeeStatusService.findById(root.get("status").asLong()))
                    .jobBreak(root.get("jobBreak").asBoolean())
                    .build();
            job.setUpdatedBy(userService.getLoggedInUser().getUsername());
            if (job != null) {
                return profileJobRepository.save(job);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public Boolean updateBreakJob(Long id, String data){
        try {
            Optional<ProfileJob> objection = profileJobRepository.findById(id);
            if (objection.isPresent()) {
                JsonNode root = mapper.readTree(data);
                ProfileJob job = objection.get();
                job.setProfile(root.get("profile") == null? null: profileService.findByIdWithoutRelation(root.get("profile").asLong()));
                job.setMaktubDate(root.get("maktubDate") == null? null: changeDate.convertPersianDateToGregorianDate(root.get("maktubDate").asText()));
                job.setEndDate(root.get("endDate") == null? null: changeDate.convertPersianDateToGregorianDate(root.get("endDate").asText()));
                job.setBreakDetail(root.get("breakDetail").asText());
                job.setJobBreak(root.get("jobBreak").asBoolean());
                job.setStatus(root.get("status") == null? null: employeeStatusService.findById(root.get("status").asLong()));
                job.setUpdatedBy(userService.getLoggedInUser().getUsername());

                if (job != null) {
                    profileJobRepository.save(job);
                }
                return true;
            }

             return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public ProfileJob findByLatestJob(Long id){
        ProfileJob newJob = profileJobRepository.findbyLastJobProfile(id);
        if(!newJob.equals(null)){
            return newJob;
        }
        return null;
    }


    public List<RevisionDTO>  getProfileJobLog(Long id){
        Revisions<Integer, ProfileJob> indList = profileJobRepository.findRevisions(id);
        List<Revision<Integer, ProfileJob>> educations = indList.getContent();

        List<RevisionDTO> dtos= new ArrayList<>();
        
        for(Revision revision: educations){
                dtos.add(new RevisionDTO(revision.getEntity()));
        }

        return dtos;
    }

}
