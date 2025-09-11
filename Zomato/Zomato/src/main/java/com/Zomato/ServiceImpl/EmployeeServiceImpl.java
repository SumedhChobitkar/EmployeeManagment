package com.Zomato.ServiceImpl;

import com.Zomato.Entity.Employee;

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

        try {
            if (employee.getName() == null && employee.getName().isEmpty()) {
                throw new InvalideDataException("enter the name ");
            }

            l.info("Saving employee with id " + employee.getId());
            return employeeRepository.save(employee);
        } catch (Exception e) {
            l.error("error while saving employee ", e);
            throw new RuntimeException("Errrrrr");


        }
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int id){
        return employeeRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Employee id not found "+id));
    }

    @Override
    public Employee updateEmployee(int id, Employee employeeDetails) {
Employee employee=getEmployeeById(id);

employee.setName(employeeDetails.getName());
employee.setAddress(employeeDetails.getAddress());
        return employeeRepository.save(employee);
    }
    @Override
    public void deleteEmployee(int id){
        employeeRepository.deleteById(id);

    }
//
//    @Override
//    public List<Employee> getByName(String name){
//        try{
//            List<Employee> emp=employeeRepository.findByName(name).orElseThrow()
//            if(emp.isEmpty()){
//                throw new EmployeeNotFoundException("error while getting by name");
//            }
//            return emp;
//        }catch(Exception e){
//
//            throw new RuntimeException("Error by name ");
//        }
//
//    }
@Override
    public String login(String email,String password){

       Employee emp=employeeRepository.findByEmail(email)
               .orElseThrow(()-> new EmployeeNotFoundException("Invalid credentials."));
       if(!emp.getPassword().equals(password)){
           throw new RuntimeException("Invalid password");
       }
        return"Log in successfully"+emp.getRole();
    }

}
