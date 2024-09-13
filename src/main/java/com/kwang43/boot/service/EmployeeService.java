package com.kwang43.boot.service;

import com.kwang43.boot.config.Response;
import com.kwang43.boot.domain.VEmployee;
import com.kwang43.boot.model.dto.EmployeeDto;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    Response<Page<VEmployee>> findAllEmployee(int page, int size, String sortField, String sortOrder);

    Response<VEmployee> findEmployeeById(Long id);

    Response<Boolean> saveEmployee(EmployeeDto employeeDto);

    Response<Boolean> deleteById(Long id);

    Response<Boolean> searchEmployeeForExport(EmployeeDto employeeDto);
}
