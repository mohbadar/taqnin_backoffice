package com.nsia.officems.gop.medical_report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalReportRepository extends JpaRepository<MedicalReport, Long> {
    public List<MedicalReport> findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByIdDesc(Long id);

}
