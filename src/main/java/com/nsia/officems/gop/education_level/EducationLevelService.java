package com.nsia.officems.gop.education_level;

import java.util.List;

public interface EducationLevelService {
    public List<EducationLevel> findAll();
    public EducationLevel findById(Long id);
    public EducationLevel create(EducationLevel level);
    public Boolean delete(Long id);
}
