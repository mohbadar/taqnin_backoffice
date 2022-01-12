package com.nsia.officems.gop.academic_degree_type;

import java.util.List;

public interface AcademicDecreeTypeService {
    public List<AcademicDecreeType> findAll();
    public AcademicDecreeType findById(Long id);
    public AcademicDecreeType create(AcademicDecreeType type);
    public Boolean delete(Long id);
}
