package com.nsia.officems.gop.medical_report;

import java.util.List;

public interface MedicalReportService {
    public List<MedicalReport> findAll();
    public MedicalReport findById(Long id);
    public MedicalReport create(String data);
    public Boolean delete(Long id);
    public Boolean update(Long id, String data); 
    public List<MedicalReport> findByProfile_id(Long id);
}
