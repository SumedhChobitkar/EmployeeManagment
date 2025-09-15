package com.Zomato.Repository;

import com.Zomato.Entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary,Long> {
}
