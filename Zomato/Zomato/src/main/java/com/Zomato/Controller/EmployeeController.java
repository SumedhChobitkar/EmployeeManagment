package com.Zomato.Controller;

import com.Zomato.Entity.Employee;
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

@RestController
@RequestMapping("/api/Employee")
public class EmployeeController {
    private static final Logger l= LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Employee employee){
try {
    Employee saveEmployee = employeeService.saveEmployee(employee);

    return ResponseEntity.status(HttpStatus.CREATED).body(saveEmployee);
}catch(InvalideDataException e){
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("insert name first");
}
catch(Exception x){
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Do not enter the id");

}

    }

    @GetMapping("/getAll")
    public List<Employee> getAllEmployee(){
        return  employeeService.getAllEmployee();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id){
        Employee employee=new Employee();

        return employeeService.getEmployeeById(id);

    }

    @PutMapping("/{id}")
    public Employee updateEmploye(@PathVariable int id,@RequestBody Employee employee){
        return employeeService.updateEmployee(id,employee);

    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
        return "employee deleted successfully";

    }
//@GetMapping("/getBynameE/{name}")
//public ResponseEntity<?> getEmployeeByName(@PathVariable String name){
//        try{
//      List<Employee>   employee=  employeeService.getByName(name);
//      return ResponseEntity.ok(employee);
//
//        }catch(EmployeeNotFoundException e) {
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
//
//        }catch
//        (Exception  e){
//            return new ResponseEntity<>("Error "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email,@RequestParam String password){
        try{
         String  response=employeeService.login(email, password);
         return ResponseEntity.ok(response);

        }catch( EmployeeNotFoundException e){

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid credentials "+e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid credentials exception "+e.getMessage());
        }
    }

}
