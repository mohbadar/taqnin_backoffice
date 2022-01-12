package com.nsia.officems.gop.profile_fired;

import java.util.List;

import com.nsia.officems.gop.profile_fired.dto.ProfileFiredDto;

public interface ProfileFiredService {
    public List<ProfileFired> findAll();
    public ProfileFired findById(Long id);
    public ProfileFired create(ProfileFiredDto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, ProfileFiredDto dto); 
    public List<ProfileFired> findByProfile_id(Long id);
}
