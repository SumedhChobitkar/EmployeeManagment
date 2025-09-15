package com.Zomato.ServiceImpl;

import com.Zomato.Entity.Salary;
import com.Zomato.Exceptions.EmployeeNotFoundException;
import com.Zomato.Exceptions.InvalideDataException;
import com.Zomato.Repository.SalaryRepository;
import com.Zomato.Service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    SalaryRepository salaryRepository;


    @Override
    public Salary saveSalary(Salary salary) {
        if(salary.getBaseSalary()<=0||salary.getNetSalary()<=0){
            throw new InvalideDataException("You Salary Should be More Than Zero");
        }
        return salaryRepository.save(salary);
    }

    @Override
    public List<Salary> getAllSalary() {
        return salaryRepository.findAll();
    }

    @Override
    public Salary getSalaryById(Long id) {
        return salaryRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Salary Id Not Found..!"));
    }

    @Override
    public Salary updateSalary(Long id, Salary salaryDetails) {
        Salary salary=getSalaryById(id);
        salary.setBaseSalary(salaryDetails.getBaseSalary());
        salary.setNetSalary(salaryDetails.getNetSalary());
        salary.setTax(salaryDetails.getTax());
        salary.setBonus(salaryDetails.getBonus());
        return salaryRepository.save(salary);
    }

    @Override
    public void deleteSalary(Long id) {
        if (!salaryRepository.existsById(id)) {
            throw new RuntimeException("Salary with ID " + id + " not found.");
        } else {

            salaryRepository.deleteById(id);
        }

    }
}
