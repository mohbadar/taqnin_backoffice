package com.nsia.officems.gop.profile_fired_type;

import java.util.List;

public interface ProfileFiredTypeService {
    public List<ProfileFiredType> findAll();
    public ProfileFiredType findById(Long id);
    public ProfileFiredType create(ProfileFiredType level);
    public Boolean delete(Long id); 
}
