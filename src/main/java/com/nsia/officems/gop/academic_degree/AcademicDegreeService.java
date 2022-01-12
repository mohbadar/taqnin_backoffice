package com.nsia.officems.gop.academic_degree;

import java.util.List;

import com.nsia.officems.gop.academic_degree.dto.AcademicDegreeDto;

public interface AcademicDegreeService {
    public List<AcademicDegree> findAll();
    public AcademicDegree findById(Long id);
    public AcademicDegree create(AcademicDegreeDto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, AcademicDegreeDto dto); 
    public List<AcademicDegree> findByProfile_id(Long id);

}
