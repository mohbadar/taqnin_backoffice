package com.nsia.officems.gop.employeeStatus;

import java.util.List;

public interface EmployeeStatusService {
    public List<EmployeeStatus> findAll();
    public EmployeeStatus findById(Long id);
    public EmployeeStatus create(EmployeeStatus status);
    public Boolean delete(Long id);
}
