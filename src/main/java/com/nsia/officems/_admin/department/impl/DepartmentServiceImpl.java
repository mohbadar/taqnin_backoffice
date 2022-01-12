package com.nsia.officems._admin.department.impl;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nsia.officems._admin.department.Department;
import com.nsia.officems._admin.department.DepartmentRepository;
import com.nsia.officems._admin.department.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public Department findById(Long id) {
        Optional<Department> optionalObj = departmentRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Department create(Department department) {
        department.setDeleted(false);
        return departmentRepository.save(department);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Department> department = departmentRepository.findById(id);

        if (department.isPresent()) {
            Department department2 = department.get();
            department2.setDeleted(true);
            // ethnic.setDeletedBy(userService.getLoggedInUser().getUsername());
            department2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            departmentRepository.save(department2);
            return true;
        }

        return false;
    }

    @Override
    public boolean update(Long id, Department department) {
        Optional<Department> departmentToBeUpdated = departmentRepository.findById(id);
        if (departmentToBeUpdated.isEmpty()) {
            return false;
        }

        Department editedDepartment = departmentToBeUpdated.get();

        editedDepartment.setNameEn(department.getNameEn());
        editedDepartment.setNameDr(department.getNameDr());
        editedDepartment.setNamePs(department.getNamePs());

        departmentRepository.save(editedDepartment);
        return true;
    }

}
