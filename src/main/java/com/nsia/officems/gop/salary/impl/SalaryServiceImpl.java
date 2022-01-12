package com.nsia.officems.gop.salary.impl;

import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import java.util.List;
import java.util.Optional;
import java.time.ZoneId;
import java.util.Date;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile_job.ProfileJob;
import com.nsia.officems.gop.profile_job.ProfileJobService;
import com.nsia.officems.gop.salary.Salary;
import com.nsia.officems.gop.salary.SalaryRepository;
import com.nsia.officems.gop.salary.SalaryService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryServiceImpl implements SalaryService{
    private ChecklistTitle titles = new ChecklistTitle();
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Autowired
    private ProfileJobService profileJobService;

    @Autowired
    private UserService userService;

    @Override
    public List<Salary> findAll() {
        return salaryRepository.findAll();
    }

    @Override
    public List<Salary> findByProfile_id(Long id) {
        return salaryRepository.findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByIdDesc(id);
    }

    @Override
    public Salary findById(Long id) {
        Optional<Salary> optionalObj = salaryRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    
    @Override
    public Salary create(String data) {
        try {
            JsonNode root = mapper.readTree(data);
            Salary salary = Salary.builder()
                    .profile(profileService.findByIdWithoutRelation(root.get("profile").asLong()))
                    .profileJob((root.get("profileJob")) == null? null: profileJobService.findById(root.get("profileJob").asLong()))
                    .originalSalary(root.get("originalSalary").asDouble())
                    .patentSalary(root.get("patentSalary").asDouble())
                    .extraSalary(root.get("extraSalary").asDouble())
                    .original(root.get("original").asDouble())
                    .macul(root.get("macul").asDouble())
                    .cadreSalary(root.get("cadreSalary").asDouble())
                    .build();
                    salary.setCreatedBy(userService.getLoggedInUser().getUsername());
            if (!salary.equals(null)) {

                profileChecklistService.update(salary.getProfile().getId(), titles.getSalary());
                return salaryRepository.save(salary);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public Boolean update(Long id, String data) {
        try {
            Optional<Salary> objection = salaryRepository.findById(id);
            if (objection.isPresent()) {
                JsonNode root = mapper.readTree(data);
                Salary salary = objection.get();
                salary.setOriginalSalary(root.get("originalSalary").asDouble());
                salary.setProfileJob((root.get("profileJob")) == null? null: profileJobService.findById(root.get("profileJob").asLong()));
                salary.setPatentSalary(root.get("patentSalary").asDouble());
                salary.setExtraSalary(root.get("extraSalary").asDouble());
                salary.setOriginal(root.get("original").asDouble());
                salary.setCadreSalary(root.get("cadreSalary").asDouble());
                salary.setMacul(root.get("macul").asDouble());
                salary.setUpdatedBy(userService.getLoggedInUser().getUsername());

                if (!salary.equals(null)) {
                    salaryRepository.save(salary);
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
        Optional<Salary> salary = salaryRepository.findById(id);

        if (salary.isPresent()) {
            Salary salary2 =  salary.get();
            salary2.setDeleted(true);
            salary2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            salaryRepository.save(salary2);
            return true;
        }

        return false;
    } 
}
