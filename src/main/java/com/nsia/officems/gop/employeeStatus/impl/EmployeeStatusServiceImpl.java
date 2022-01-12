package com.nsia.officems.gop.employeeStatus.impl;

import com.nsia.officems.gop.employeeStatus.EmployeeStatus;
import com.nsia.officems.gop.employeeStatus.EmployeeStatusRepository;
import com.nsia.officems.gop.employeeStatus.EmployeeStatusService;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeStatusServiceImpl implements EmployeeStatusService{
    @Autowired
    private EmployeeStatusRepository employeeStatusRepository;


    @Override
    public List<EmployeeStatus> findAll() {
        return employeeStatusRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public EmployeeStatus findById(Long id) {
        Optional<EmployeeStatus> optionalObj = employeeStatusRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public EmployeeStatus create(EmployeeStatus status) {
        return employeeStatusRepository.save(status);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<EmployeeStatus> status = employeeStatusRepository.findById(id);

        if (status.isPresent()) {
            EmployeeStatus status2 = status.get();
            status2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            status2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            employeeStatusRepository.save(status2);
            return true;
        }

        return false;
    }
}
