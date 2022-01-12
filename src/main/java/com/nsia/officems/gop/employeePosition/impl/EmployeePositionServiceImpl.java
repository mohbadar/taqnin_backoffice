package com.nsia.officems.gop.employeePosition.impl;

import com.nsia.officems.gop.employeePosition.EmployeePosition;
import com.nsia.officems.gop.employeePosition.EmployeePositionRepository;
import com.nsia.officems.gop.employeePosition.EmployeePositionService;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeePositionServiceImpl implements EmployeePositionService{
    @Autowired
    private EmployeePositionRepository employeePositionRepository;


    @Override
    public List<EmployeePosition> findAll() {
        return employeePositionRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public EmployeePosition findById(Long id) {
        Optional<EmployeePosition> optionalObj = employeePositionRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public EmployeePosition create(EmployeePosition position) {
        return employeePositionRepository.save(position);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<EmployeePosition> position = employeePositionRepository.findById(id);

        if (position.isPresent()) {
            EmployeePosition position1 = position.get();
            position1.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            position1.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            employeePositionRepository.save(position1);
            return true;
        }

        return false;
    }
    
}
