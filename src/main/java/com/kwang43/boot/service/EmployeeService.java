package com.kwang43.boot.service;

import com.kwang43.boot.domain.Employee;
import com.kwang43.boot.domain.VEmployee;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    Page<VEmployee> findAllEmployee(int page, int size, String sortField, String sortOrder);

    VEmployee findById(Long id);

    Boolean saveEmployee(Employee employee);

    Boolean deleteById(Long id);

//    Object login(String username, String password, String code, String uuid);
}
