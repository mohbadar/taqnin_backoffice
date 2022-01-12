package com.nsia.officems.gop.employee_militrary_grade;

import java.util.List;

public interface EmployeeMilitaryGradeService {
    public List<EmployeeMilitaryGrade> findAll();
    public EmployeeMilitaryGrade findById(Long id);
    public EmployeeMilitaryGrade create(EmployeeMilitaryGrade grade);
    public Boolean delete(Long id);
}
