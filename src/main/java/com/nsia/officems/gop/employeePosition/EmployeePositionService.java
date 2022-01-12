package com.nsia.officems.gop.employeePosition;

import java.util.List;

public interface EmployeePositionService {
    public List<EmployeePosition> findAll();
    public EmployeePosition findById(Long id);
    public EmployeePosition create(EmployeePosition position);
    public Boolean delete(Long id);  
}
