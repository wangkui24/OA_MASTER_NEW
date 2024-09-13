package com.kwang43.boot.service.impl;

import com.kwang43.boot.config.Response;
import com.kwang43.boot.core.MessageCode;
import com.kwang43.boot.domain.Employee;
import com.kwang43.boot.domain.VEmployee;
import com.kwang43.boot.model.dto.EmployeeDto;
import com.kwang43.boot.repository.EmployeeRepository;
import com.kwang43.boot.repository.VEmployeeRepository;
import com.kwang43.boot.service.EmployeeService;
import com.kwang43.boot.utils.HttpStatus;
import com.kwang43.boot.utils.PageableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Response<Page<VEmployee>> findAllEmployee(int page, int size, String sortField, String sortOrder) {
        try {
            Pageable pageable = PageableUtil.createPageable(page, size, sortField, sortOrder);
            Page<VEmployee> vEmployeeRepositoryAll = vEmployeeRepository.findAll(pageable);
            return new Response<>(vEmployeeRepositoryAll);
        } catch (Exception e) {
            log.error("findAllEmployee error [{}]", e.getMessage());
            return new Response<>(HttpStatus.ERROR, MessageCode.System.SERVER_ERROR);
        }
    }

    @Override
    public Response<VEmployee> findEmployeeById(Long id) {
        try {
            VEmployee vEmployee = vEmployeeRepository.findById(id).orElse(null);
            if (vEmployee != null) {
                return new Response<>(vEmployee);
            } else {
                return new Response<>(HttpStatus.NO_CONTENT, MessageCode.Employee.NOT_FOUND_EMPLOYEE);
            }
        } catch (Exception e) {
            log.error("findEmployeeById error [{}]", e.getMessage());
            return new Response<>(HttpStatus.ERROR, MessageCode.System.SERVER_ERROR);
        }
    }


    public Response<Boolean> saveEmployee(EmployeeDto employeeDto) {
        try {
            List<VEmployee> vEmployeeByCellphone = vEmployeeRepository.findByCellphone(employeeDto.getCellphone());
            if (!vEmployeeByCellphone.isEmpty()) {
                return new Response<>(HttpStatus.HAS_EXISTED, MessageCode.Employee.MOBILE_NUMBER_HAS_EXISTED);
            }
            List<VEmployee> vEmployeeByEmail = vEmployeeRepository.findByEmail(employeeDto.getCellphone());
            if (!vEmployeeByEmail.isEmpty()) {
                return new Response<>(HttpStatus.HAS_EXISTED, MessageCode.Employee.EMAIL_HAS_EXISTED);
            }
            Employee employee = saveEmployeeByDto(employeeDto);
            employeeRepository.save(employee);
            log.info("saveEmployee successfully");
            return new Response<>(true);
        } catch (Exception e) {
            log.error("saveEmployee error [{}]", e.getMessage());
            return new Response<>(HttpStatus.ERROR, MessageCode.System.SERVER_ERROR);
        }
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
        employee.setSchoolId(employeeDto.getSchoolId());
        employee.setEmploymentDatetime(employeeDto.getEmploymentDatetime());
        employee.setCreateDatetime(employeeDto.getCreateDatetime());
        employee.setCreateBy(employeeDto.getName());
        employee.setUpdateDatetime(employeeDto.getUpdateDatetime());
        employee.setUpdateBy(employeeDto.getUpdateBy());
        employee.setRemark(employeeDto.getRemark());
        return employee;
    }

    @Override
    public Response<Boolean> deleteById(Long id) {
        try {
            VEmployee vEmployee = vEmployeeRepository.findById(id).orElse(null);
            if (vEmployee != null) {
                employeeRepository.deleteById(id);
                return new Response<>(true);
            } else {
                return new Response<>(HttpStatus.NO_CONTENT, MessageCode.Employee.NOT_FOUND_EMPLOYEE);
            }
        } catch (Exception e) {
            log.error("deleteEmployeeById error [{}]", e.getMessage());
            return new Response<>(HttpStatus.ERROR, MessageCode.System.SERVER_ERROR);
        }
    }

    @Override
    public Response<Boolean> searchEmployeeForExport(EmployeeDto employeeDto) {
        try {
            System.out.println("--START TO EXPORT EMPLOYEE--");
            return new Response<>(true);
        } catch (Exception e) {
            log.error("search employee ForExport error [{}]", e.getMessage());
            return new Response<>(HttpStatus.ERROR, MessageCode.Employee.EXPORT_EMPLOYEE_FAILED);
        }
    }
}
