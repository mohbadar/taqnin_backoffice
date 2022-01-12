package com.nsia.officems._admin.department;

import java.util.List;

public interface DepartmentService {
    public List<Department> findAll();

    public Department findById(Long id);

    public Department create(Department department);

    public Boolean delete(Long id);

    public boolean update(Long id, Department department);

}
