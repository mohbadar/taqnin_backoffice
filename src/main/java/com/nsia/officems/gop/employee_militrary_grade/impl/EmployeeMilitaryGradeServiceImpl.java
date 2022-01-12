package com.nsia.officems.gop.employee_militrary_grade.impl;


import com.nsia.officems.gop.employeeGrade.EmployeeGrade;
import com.nsia.officems.gop.employeeGrade.EmployeeGradeRepository;
import com.nsia.officems.gop.employeeGrade.EmployeeGradeService;
import com.nsia.officems.gop.employee_militrary_grade.EmployeeMilitaryGrade;
import com.nsia.officems.gop.employee_militrary_grade.EmployeeMilitaryGradeRepository;
import com.nsia.officems.gop.employee_militrary_grade.EmployeeMilitaryGradeService;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMilitaryGradeServiceImpl implements EmployeeMilitaryGradeService{

    @Autowired
    private EmployeeMilitaryGradeRepository employeeMilitaryGradeRepository;


    @Override
    public List<EmployeeMilitaryGrade> findAll() {
        return employeeMilitaryGradeRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public EmployeeMilitaryGrade findById(Long id) {
        Optional<EmployeeMilitaryGrade> optionalObj = employeeMilitaryGradeRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public EmployeeMilitaryGrade create(EmployeeMilitaryGrade grade) {
        return employeeMilitaryGradeRepository.save(grade);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<EmployeeMilitaryGrade> grade = employeeMilitaryGradeRepository.findById(id);

        if (grade.isPresent()) {
            EmployeeMilitaryGrade grade2 = grade.get();
            grade2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            grade2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            employeeMilitaryGradeRepository.save(grade2);
            return true;
        }

        return false;
    }
    
}
