package com.Zomato.Service;

import com.Zomato.Entity.Employee;
import com.Zomato.Entity.Salary;

import java.util.List;

public interface SalaryService {

    public Salary saveSalary(Salary salary);
    public List<Salary> getAllSalary();
    public Salary getSalaryById(Long id);
    public Salary updateSalary(Long id,Salary salaryDetails);

    public void deleteSalary(Long id);
}
