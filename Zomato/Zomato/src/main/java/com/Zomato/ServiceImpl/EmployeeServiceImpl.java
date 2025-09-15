package com.Zomato.ServiceImpl;

import com.Zomato.Entity.Employee;

import com.Zomato.Exceptions.EmployeeDeleteException;
import com.Zomato.Exceptions.EmployeeNotFoundException;
import com.Zomato.Exceptions.InvalideDataException;
import com.Zomato.Repository.EmployeeRepository;
import com.Zomato.Service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

private static final Logger l= LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
            if (employee.getName() == null || employee.getName().isEmpty()) {
                throw new InvalideDataException("Enter the Name");
            }
            if (employee.getRole() == null ) {
                throw new InvalideDataException("Role is required");
            }
            if (employee.getAddress() == null || employee.getAddress().isEmpty()) {
                throw new InvalideDataException("Enter the Address");
            }
            if (employee.getMobNo() == null||employee.getMobNo().isEmpty() ) {
                throw new InvalideDataException("Enter Mobile Number");
            }
            if (employee.getPanNo() == null ) {
                throw new InvalideDataException("Enter Pan Number");
            }
            if (!employee.getEmail().contains("@") || !employee.getEmail().contains(".")) {
                throw new RuntimeException("Invalid email format");
            }
            try {
                l.info("Saving Employee with Name "+employee.getName());
                return employeeRepository.save(employee);
            }catch (Exception e){
                l.error("Error while saving User",e);
                throw new RuntimeException("Don't Enter User id");
            }
    }

    @Override
    public List<Employee> getAllEmployee() {
        try {
            List<Employee> e = employeeRepository.findAll();
            if (e.isEmpty()) {
                throw new EmployeeNotFoundException("Employee Not Found");
            }
            return e;
        } catch (Exception e) {
            l.info("Error While getting Employee " + e);
            throw new RuntimeException("Database Are Empty");
        }
    }

    @Override
    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Employee id not found "+id));
    }

    @Override
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = getEmployeeById(id);

        if (employeeDetails.getName() != null)
            employee.setName(employeeDetails.getName());

        if (employeeDetails.getRole() != null)
            employee.setRole(employeeDetails.getRole());

        if (employeeDetails.getAddress() != null)
            employee.setAddress(employeeDetails.getAddress());

        if (employeeDetails.getEmail() != null)
            employee.setEmail(employeeDetails.getEmail());

        if (employeeDetails.getMobNo() != null)
            employee.setMobNo(employeeDetails.getMobNo());

        if (employeeDetails.getPanNo() != null)
            employee.setPanNo(employeeDetails.getPanNo());

        return employeeRepository.save(employee);
    }


    @Override
    public void deleteEmployee(Long id){
        try {
            if (!employeeRepository.existsById(id)) {
                throw new EmployeeDeleteException("Employee not Found with id " + id);
            }
            employeeRepository.deleteById(id);
            l.info("Delete Employee with id " + id);
        } catch (EmployeeDeleteException e) {
            throw new RuntimeException("Error while deleting employee");
        }

    }

    @Override
    public Optional<Employee> getByName(String name){
        if (name == null || name.trim().isEmpty()) {
            l.warn("Enter Restaurant Name First");   // console message
            throw new EmployeeNotFoundException("Enter Employee Name"); // send to Postman
        }
        Optional<Employee> empList = employeeRepository.findByName(name);
        if (empList.isEmpty()) {
            l.warn("Invalid Role: {}", name);  // console message
            throw new EmployeeNotFoundException("Invalid Name"); // send to Postman
        }
        return empList;

    }


}
