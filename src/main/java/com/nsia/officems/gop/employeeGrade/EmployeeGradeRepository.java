package com.nsia.officems.gop.employeeGrade;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeGradeRepository extends JpaRepository<EmployeeGrade, Long> {

	public List<EmployeeGrade> findByDeletedFalseOrDeletedIsNullAndActiveTrue(); 
}
