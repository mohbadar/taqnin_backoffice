package com.nsia.officems.gop.salary;

import java.util.List;

public interface SalaryService {
    public List<Salary> findAll();
    public Salary findById(Long id);
    public Salary create(String data);
    public Boolean delete(Long id);
    public Boolean update(Long id, String data); 
    public List<Salary> findByProfile_id(Long id);
}
