package com.nsia.officems.gop.education_level;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationLevelRepository extends JpaRepository<EducationLevel, Long> {
    public List<EducationLevel> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

}
