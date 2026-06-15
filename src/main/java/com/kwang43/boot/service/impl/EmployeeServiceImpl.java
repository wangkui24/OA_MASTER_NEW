package com.kwang43.boot.service.impl;

import com.kwang43.boot.config.ResourceNotFoundException;
import com.kwang43.boot.config.Response;
import com.kwang43.boot.core.MessageCode;
import com.kwang43.boot.domain.Employee;
import com.kwang43.boot.domain.VEmployee;
import com.kwang43.boot.model.dto.EmployeeDto;
import com.kwang43.boot.model.dto.PageResult;
import com.kwang43.boot.repository.EmployeeRepository;
import com.kwang43.boot.repository.VEmployeeRepository;
import com.kwang43.boot.service.EmployeeService;
import com.kwang43.boot.utils.HttpStatus;
import com.kwang43.boot.utils.PageableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private VEmployeeRepository vEmployeeRepository;

    @Override
    public PageResult<VEmployee> findAllEmployee(int page, int size, String sortField, String sortOrder) {
        try {
            Pageable pageable = PageableUtil.createPageable(page, size, sortField, sortOrder);
            Page<VEmployee> vEmployeeRepositoryAll = vEmployeeRepository.findAll(pageable);
            return new PageResult<>(vEmployeeRepositoryAll);
        } catch (Exception e) {
            log.error("findAllEmployee error [{}]", e.getMessage());
            throw new DataIntegrityViolationException(MessageCode.System.SERVER_ERROR);
        }
    }


    @Override
    public VEmployee findEmployeeById(Long id) {
        return vEmployeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageCode.Employee.NOT_FOUND_EMPLOYEE));
    }


    public Boolean saveEmployee(EmployeeDto employeeDto) {
        List<VEmployee> vEmployeeByCellphone = vEmployeeRepository.findByCellphone(employeeDto.getCellphone());
        if (!vEmployeeByCellphone.isEmpty()) {
            throw new DataIntegrityViolationException(MessageCode.Employee.MOBILE_NUMBER_HAS_EXISTED);
        }
        List<VEmployee> vEmployeeByEmail = vEmployeeRepository.findByEmail(employeeDto.getCellphone());
        if (!vEmployeeByEmail.isEmpty()) {
            throw new DataIntegrityViolationException(MessageCode.Employee.EMAIL_HAS_EXISTED);
        }
        Employee employee = saveEmployeeByDto(employeeDto);
        employeeRepository.save(employee);
        log.info("saveEmployee successfully");
        return true;
    }


    private static Employee saveEmployeeByDto(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setNickName(employeeDto.getNickName());
        employee.setCellphone(employeeDto.getCellphone());
        employee.setEmail(employeeDto.getEmail());
        employee.setGender(employeeDto.getGender());
        employee.setStatus(employeeDto.getStatus());
        employee.setDeptId(employeeDto.getDeptId());
        employee.setOnboardDate(employeeDto.getOnboardDate());
        employee.setCreateDatetime(employeeDto.getCreateDatetime());
        employee.setCreateBy(employeeDto.getName());
        employee.setUpdateDatetime(employeeDto.getUpdateDatetime());
        employee.setUpdateBy(employeeDto.getUpdateBy());
        employee.setRemark(employeeDto.getRemark());
        return employee;
    }


    @Override
    public Boolean deleteById(Long id) {
        VEmployee vEmployee = vEmployeeRepository.findById(id).orElse(null);
        if (vEmployee != null) {
            employeeRepository.deleteById(id);
            return true;
        } else {
            throw new DataIntegrityViolationException(MessageCode.Employee.NOT_FOUND_EMPLOYEE);
        }
    }


    @Override
    public Boolean searchEmployeeForExport(EmployeeDto employeeDto) {
        try {
            System.out.println("--START TO EXPORT EMPLOYEE--");
            return true;
        } catch (Exception e) {
            log.error("search employee ForExport error [{}]", e.getMessage());
            throw new DataIntegrityViolationException(MessageCode.Employee.EXPORT_EMPLOYEE_FAILED);
        }
    }
}
