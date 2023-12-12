package com.kwang43.boot.service;

import com.kwang43.boot.domain.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAllEmployee();

    Employee findById(Long id);

    Boolean saveEmployee(Employee employee);

    Boolean deleteById(Long id);

}
