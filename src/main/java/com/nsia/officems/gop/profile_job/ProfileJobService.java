package com.nsia.officems.gop.profile_job;

import java.util.List;
import java.util.Map;

import com.nsia.officems.gop.profile.Dto.RevisionDTO;


public interface ProfileJobService {
    public ProfileJob findById(Long id);
    public ProfileJob create(Map<String,Object> map);
    public Boolean delete(Long id);
    public Boolean update(Map<String,Object> map); 
    public List<ProfileJob> findByProfile_id(Long id);
    public ProfileJob findByLatestJob(Long id);
    public Boolean save(ProfileJob job);
    public List<ProfileJob> findByProfile_idEndDateIsNull(Long id);
    public ProfileJob createBreakJob(String data);
    public Boolean updateBreakJob(Long id, String data); 
    public Map<String,Object> calculteWorkExperienceByProfile(Long proId);

    public List<RevisionDTO>  getProfileJobLog(Long id);
}
