package com.nsia.officems.gop.employeeGrade;

import java.util.List;

public interface EmployeeGradeService {
    public List<EmployeeGrade> findAll();
    public EmployeeGrade findById(Long id);
    public EmployeeGrade create(EmployeeGrade grade);
    public Boolean delete(Long id);
}
