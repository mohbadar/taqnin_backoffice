package com.nsia.officems.gop.profile_retirement;

import java.util.List;

import com.nsia.officems.gop.profile_retirement.dto.ProfileResignationDto;

public interface ProfileRetirementService {
    public List<ProfileRetirement> findAll();
    public ProfileRetirement findById(Long id);
    public ProfileRetirement create(ProfileResignationDto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, ProfileResignationDto dto); 
    public List<ProfileRetirement> findByProfile_id(Long id); 
}
