package com.Zomato.Controller;

import com.Zomato.Entity.Employee;
import com.Zomato.Exceptions.EmployeeDeleteException;
import com.Zomato.Exceptions.EmployeeNotFoundException;
import com.Zomato.Exceptions.InvalideDataException;
import com.Zomato.Service.EmployeeService;
import com.Zomato.ServiceImpl.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Employee")
public class EmployeeController {
    private static final Logger l= LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Employee employee){
        try {
            Employee emp = employeeService.saveEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(emp);
        }catch (Exception e){
            l.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllEmployee(){
        try {
            List<Employee> empList=employeeService.getAllEmployee();
            return ResponseEntity.ok(empList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
        try{
            Employee e=employeeService.getEmployeeById(id);
            return ResponseEntity.ok(e);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
        return employeeService.updateEmployee(id,employee);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Employee deleted successfully with id " + id);
        } catch (EmployeeDeleteException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            l.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }

    }
    @GetMapping("/getBynameE/{name}")
        public ResponseEntity<?> getEmployeeByName(@PathVariable String name) {
        try {
            Optional<Employee> employee = employeeService.getByName(name);
            return ResponseEntity.ok(employee);

        } catch (EmployeeNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        } catch
        (Exception e) {
            return new ResponseEntity<>("Error " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
