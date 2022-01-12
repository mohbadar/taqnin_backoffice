package com.nsia.officems.gop.academic_degree;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicDegreeRepository extends JpaRepository<AcademicDegree, Long> {
    public List<AcademicDegree> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    public List<AcademicDegree> findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByIdDesc(Long id);

}
