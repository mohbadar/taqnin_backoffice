package com.nsia.officems.gop.employee_militrary_grade;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeMilitaryGradeRepository extends JpaRepository<EmployeeMilitaryGrade, Long> {
	public List<EmployeeMilitaryGrade> findByDeletedFalseOrDeletedIsNullAndActiveTrue(); 
}
