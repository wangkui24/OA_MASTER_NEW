package com.kwang43.boot.controller;

import com.kwang43.boot.config.Resource;
import com.kwang43.boot.domain.Employee;
import com.kwang43.boot.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
    private EmployeeService employeeService;

    @GetMapping("")
    @ApiOperation(value="获取员工列表", notes="")
    public Resource<Page<Employee>>findEmployees(@RequestParam(value = "page", defaultValue = "0") int page,
                                                 @RequestParam(value = "size", defaultValue = "10") int size,
                                                 @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                                 @RequestParam(value = "sortOrder", defaultValue = "asc") String sortOrder) {
        return new Resource<>(employeeService.findAllEmployee(page,size,sortField,sortOrder));
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value="按id查询员工信息", notes="")
    public Resource<Employee>findEmployeeById(@PathVariable("id") Long id) {
        return new Resource<>(employeeService.findById(id));
    }

    /**
     * 注意:记得添加@RequestBody注解,否则前端传递来的json数据无法被封装到User中!
     */
    @PostMapping("")
    @ApiOperation(value="添加员工", notes="")
    public Resource<Boolean>addEmployee(@RequestBody Employee employee) {
        return new Resource<>(employeeService.saveEmployee(employee));
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value="按id删除员工", notes="")
    public Resource<Boolean>deleteById(@PathVariable("id") Long id) {
        return new Resource<>(employeeService.deleteById(id));
    }

}