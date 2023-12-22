package com.kwang43.boot.service.impl;

import com.kwang43.boot.config.PageableUtil;
import com.kwang43.boot.domain.Employee;
import com.kwang43.boot.repository.EmployeeRepository;
import com.kwang43.boot.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 功能：
 * 作者：kwang43
 * 日期：2023/10/12 11:06
 */
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Page<Employee> findAllEmployee(int page, int size, String sortField, String sortOrder) {
        Pageable pageable = PageableUtil.createPageable(page, size, sortField, sortOrder);
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    @Override
    public Boolean saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return true;
    }

    @Override
    public Boolean deleteById(Long id) {
        employeeRepository.deleteById(id);
        return true;
    }
}
