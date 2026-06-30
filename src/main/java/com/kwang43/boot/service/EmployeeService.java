package com.kwang43.boot.service;

import com.kwang43.boot.domain.VEmployee;
import com.kwang43.boot.model.dto.EmployeeDto;
import com.kwang43.boot.model.dto.EmployeeQueryDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    Page<VEmployee> findAllEmployee(EmployeeQueryDto employeeQueryDto);

    List<VEmployee> searchEmployeeExport(EmployeeQueryDto employeeQueryDto);

    VEmployee findEmployeeById(Long id);

    Boolean saveEmployee(EmployeeDto employeeDto);

    Boolean deleteById(Long id);

    Boolean searchEmployeeForExport(EmployeeDto employeeDto);
}
