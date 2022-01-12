package com.nsia.officems.gop.salary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
    public List<Salary> findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByIdDesc(Long id);

}
