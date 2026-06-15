package com.kwang43.boot.service;

import com.kwang43.boot.config.Response;
import com.kwang43.boot.domain.VEmployee;
import com.kwang43.boot.model.dto.EmployeeDto;
import com.kwang43.boot.model.dto.PageResult;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    PageResult<VEmployee> findAllEmployee(int page, int size, String sortField, String sortOrder);

    VEmployee findEmployeeById(Long id);

    Boolean saveEmployee(EmployeeDto employeeDto);

    Boolean deleteById(Long id);

    Boolean searchEmployeeForExport(EmployeeDto employeeDto);
}
