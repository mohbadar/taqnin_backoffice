package com.nsia.officems.gop.employeePosition;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, Long> {

    public List<EmployeePosition> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
}

