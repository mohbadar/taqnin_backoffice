package com.nsia.officems.gop.profile_retirement_type;

import java.util.List;

public interface ProfileRetirementTypeService {
    public List<ProfileRetirementType> findAll();
    public ProfileRetirementType findById(Long id);
    public ProfileRetirementType create(ProfileRetirementType level);
    public Boolean delete(Long id);
    
}
