package com.nsia.officems.gop.Profile_checklist;

import java.util.List;

public interface ProfileChecklistService {
    public List<ProfileChecklist> findAll();
    public ProfileChecklist findById(Long id);
    public Boolean create(Long profileId);
    public Boolean delete(Long id);
    public Boolean update(Long profileId, String title); 
    public Boolean updateprofileSetting(Long profileId, String title, Boolean status);
    public List<ProfileChecklist> findByProfile_id(Long id); 
}
