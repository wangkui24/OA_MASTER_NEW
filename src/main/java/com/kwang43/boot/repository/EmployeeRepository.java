package com.kwang43.boot.repository;

import com.kwang43.boot.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByName(String name);

}
