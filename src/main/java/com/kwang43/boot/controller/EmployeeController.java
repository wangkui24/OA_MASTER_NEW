package com.kwang43.boot.controller;

import com.kwang43.boot.domain.Employee;
import com.kwang43.boot.repository.EmployeeRepository;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 功能：
 * 作者：kwang43
 * 日期：2023/10/11 15:19
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("")
    @ApiOperation(value="获取员工列表", notes="")
    public List<Employee> findEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value="按id查询员工信息", notes="")
    public Optional<Employee> findEmployeeById(@PathVariable("id") Long id) {
        return employeeRepository.findById(id);
    }

    /**
     * 注意:记得添加@RequestBody注解,否则前端传递来的json数据无法被封装到User中!
     */
    @PostMapping("")
    @ApiOperation(value="添加员工", notes="")
    public Employee addEmployee(@RequestBody Employee employee) {
        return  employeeRepository.save(employee);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value="按Id删除员工", notes="")
    public String deleteById(@PathVariable("id") Long id) {
        employeeRepository.deleteById(id);
        return "success";
    }
}