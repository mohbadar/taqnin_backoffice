package com.nsia.officems.gop.medical_report.impl;

import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import com.nsia.officems.gop.medical_report.MedicalReport;
import com.nsia.officems.gop.medical_report.MedicalReportRepository;
import com.nsia.officems.gop.medical_report.MedicalReportService;
import java.util.List;
import java.util.Optional;
import java.time.ZoneId;
import java.util.Date;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.gop.profile.ProfileService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalReportServiceImpl implements MedicalReportService {
    private ChecklistTitle titles = new ChecklistTitle();
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MedicalReportRepository medicalReportRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Autowired
    private UserService userService;

    @Override
    public List<MedicalReport> findAll() {
        return medicalReportRepository.findAll();
    }

    @Override
    public List<MedicalReport> findByProfile_id(Long id) {
        return medicalReportRepository.findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByIdDesc(id);
    }

    @Override
    public MedicalReport findById(Long id) {
        Optional<MedicalReport> optionalObj = medicalReportRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public MedicalReport create(String data) {
        try {
            JsonNode root = mapper.readTree(data);
            MedicalReport report = MedicalReport.builder()
                    .profile(profileService.findByIdWithoutRelation(root.get("profile").asLong()))
                    .medicalReport(root.get("medicalReport").asBoolean()).build();
                    report.setCreatedBy(userService.getLoggedInUser().getUsername());
            if (!report.equals(null)) {

                profileChecklistService.update(report.getProfile().getId(), titles.getMedical());
                return medicalReportRepository.save(report);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public Boolean update(Long id, String data) {
        try {
            Optional<MedicalReport> objection = medicalReportRepository.findById(id);
            if (objection.isPresent()) {
                JsonNode root = mapper.readTree(data);
                MedicalReport report = objection.get();
                report.setMedicalReport(root.get("medicalReport").asBoolean());
                report.setUpdatedBy(userService.getLoggedInUser().getUsername());

                if (!report.equals(null)) {
                    medicalReportRepository.save(report);
                    return true;
                }
                return false;
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public Boolean delete(Long id) {
        Optional<MedicalReport> medical = medicalReportRepository.findById(id);

        if (medical.isPresent()) {
            MedicalReport medicalReport = medical.get();
            medicalReport.setDeleted(true);
            medicalReport.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            medicalReportRepository.save(medicalReport);
            return true;
        }

        return false;
    }

}
