package com.nsia.officems.odf.odf_subject;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OdfSubjectRepository extends JpaRepository<OdfSubject, Long> {
    public List<OdfSubject> findByResolution_id(Long id);

    public List<OdfSubject> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
}
