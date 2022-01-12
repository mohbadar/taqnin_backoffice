package com.nsia.officems.gop.employeeStatus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeStatusRepository extends JpaRepository<EmployeeStatus, Long> {

	public List<EmployeeStatus> findByDeletedFalseOrDeletedIsNullAndActiveTrue(); 
}
