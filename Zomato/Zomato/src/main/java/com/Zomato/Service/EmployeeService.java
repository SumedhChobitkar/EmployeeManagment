package com.Zomato.Service;

import com.Zomato.Entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public Employee saveEmployee(Employee employee);
    public List<Employee> getAllEmployee();
    public Employee getEmployeeById(Long id);
    public Employee updateEmployee(Long id,Employee employeeDetails);

    public void deleteEmployee(Long id);

    public Optional<Employee> getByName(String name);







}
