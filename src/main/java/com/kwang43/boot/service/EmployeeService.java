package com.kwang43.boot.service;

import com.kwang43.boot.domain.Employee;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    Page<Employee> findAllEmployee(int page, int size, String sortField, String sortOrder);

    Employee findById(Long id);

    Boolean saveEmployee(Employee employee);

    Boolean deleteById(Long id);

}
