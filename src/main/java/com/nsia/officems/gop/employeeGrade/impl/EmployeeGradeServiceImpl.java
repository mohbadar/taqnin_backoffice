package com.nsia.officems.gop.employeeGrade.impl;
import com.nsia.officems.gop.employeeGrade.EmployeeGrade;
import com.nsia.officems.gop.employeeGrade.EmployeeGradeRepository;
import com.nsia.officems.gop.employeeGrade.EmployeeGradeService;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeGradeServiceImpl implements EmployeeGradeService{ 
    @Autowired
    private EmployeeGradeRepository employeeGradeRepository;


    @Override
    public List<EmployeeGrade> findAll() {
        return employeeGradeRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public EmployeeGrade findById(Long id) {
        Optional<EmployeeGrade> optionalObj = employeeGradeRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public EmployeeGrade create(EmployeeGrade grade) {
        return employeeGradeRepository.save(grade);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<EmployeeGrade> grade = employeeGradeRepository.findById(id);

        if (grade.isPresent()) {
            EmployeeGrade grade2 = grade.get();
            grade2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            grade2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            employeeGradeRepository.save(grade2);
            return true;
        }

        return false;
    }
}
    

