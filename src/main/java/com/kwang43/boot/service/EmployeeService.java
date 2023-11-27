package com.kwang43.boot.service;

import com.kwang43.boot.domain.Employee;
import com.kwang43.boot.model.dto.EmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAllEmployee();

    Employee findById(Long id);

    Boolean saveEmployee(Employee employee);

    Boolean deleteById(Long id);

//    Page<EmployeeDto> findEmployeeBySearchDto(EmployeeQueryDto search);

    Page<EmployeeDto> findByDeptId(Integer deptId, Pageable pageable);
}
