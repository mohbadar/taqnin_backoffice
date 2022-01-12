package com.nsia.officems.gop.profile_education;

import java.util.List;

import com.nsia.officems.gop.profile.Dto.RevisionDTO;
import com.nsia.officems.gop.profile_education.Dto.EducationDto;

public interface EducationService {
    public List<Education> findAll();

    public Education findById(Long id);

    public Education create(EducationDto dto);

    public Boolean delete(Long id);

    public Boolean update(Long id, EducationDto dto);

    public List<Education> findByProfile_id(Long id);

    public Education findbyEducationProfile(Long proId);

    public List<Education> findbyProfileJob_id(Long id);
    public List<RevisionDTO>  getEductionLog(Long id);
}
