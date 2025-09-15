package com.Zomato.Controller;

import com.Zomato.Entity.Employee;
import com.Zomato.Entity.Salary;
import com.Zomato.Exceptions.EmployeeNotFoundException;
import com.Zomato.Exceptions.InvalideDataException;
import com.Zomato.Service.EmployeeService;
import com.Zomato.Service.SalaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary")
public class SalaryController {

    private static final Logger l= LoggerFactory.getLogger(SalaryController.class);


    @Autowired
    SalaryService salaryService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Salary salary) {

        try {
            Salary savedSalary = salaryService.saveSalary(salary);
            return ResponseEntity.ok(savedSalary);
        } catch (InvalideDataException e){
         return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST)  ;
        }catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getAllSalary")
    public ResponseEntity<?> getAllSalary(){
        try{
            List<Salary> s=salaryService.getAllSalary();
            return  ResponseEntity.ok(s);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getSalaryById/{id}")
    public ResponseEntity<?> getSalaryById(@PathVariable Long id){
        try{
            Salary salary= salaryService.getSalaryById(id);
            return ResponseEntity.ok(salary);
        }catch (EmployeeNotFoundException e){
           return  new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @PutMapping("/updateSalary/{id}")
    public ResponseEntity<?> updateSalary(@PathVariable Long id, @RequestBody Salary salaryDetails) {
      try{
          salaryService.updateSalary(id, salaryDetails);
          return ResponseEntity.ok("Successfully Updated...!");
      } catch (RuntimeException e) {
          throw new RuntimeException(e);
      }


    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteSalaryById(@PathVariable Long id) {
        try {
            salaryService.deleteSalary(id);
            return ResponseEntity.ok("Salary deleted successfully.");
        } catch (EmployeeNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
