package com.nsia.officems.gop.academic_degree_type;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicDecreeTypeRepository extends JpaRepository<AcademicDecreeType, Long> {
    public List<AcademicDecreeType> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

}
