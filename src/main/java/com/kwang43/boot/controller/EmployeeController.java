package com.kwang43.boot.controller;

import com.kwang43.boot.config.PagedResource;
import com.kwang43.boot.config.Response;
import com.kwang43.boot.domain.VEmployee;
import com.kwang43.boot.model.dto.EmployeeQueryDto;
import com.kwang43.boot.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @PostMapping("/pageable")
    @ApiOperation(value="获取员工列表", notes="")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE_READONLY', 'EMPLOYEE_MANAGEMENT')")
    public PagedResource<VEmployee> findAllEmployee(@RequestBody EmployeeQueryDto employeeQueryDto) {
        return new PagedResource<>(employeeService.findAllEmployee(employeeQueryDto), "page", "perPage");
    }

    @PostMapping("/searchEmployeeExport")
    @ApiOperation(value="导出员工列表", notes="")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE_EXPORT', 'EMPLOYEE_MANAGEMENT')")
    public Response<List<VEmployee>> searchEmployeeExport(@RequestBody EmployeeQueryDto employeeQueryDto) {
        return new Response<>(employeeService.searchEmployeeExport(employeeQueryDto));
    }


    @GetMapping(path = "/{id}")
    @ApiOperation(value="按id查询员工信息", notes="")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE_READONLY', 'EMPLOYEE_MANAGEMENT')")
    public Response<VEmployee> findEmployeeById(@PathVariable("id") Long id) {
        return new Response<>(employeeService.findEmployeeById(id));
    }


    /**
     * 注意:记得添加@RequestBody注解,否则前端传递来的json数据无法被封装到User中!
     */
//    @PostMapping("")
//    @ApiOperation(value="添加员工", notes="")
//    @PreAuthorize("hasAuthority('EMPLOYEE_MANAGEMENT')")
//    public Response<Boolean> addEmployee(@RequestBody EmployeeDto employeeDto) {
//        return new Response<>(employeeService.saveEmployee(employeeDto));
//    }


    @DeleteMapping(path = "/{id}")
    @ApiOperation(value="按id删除员工", notes="")
    @PreAuthorize("hasAuthority('EMPLOYEE_MANAGEMENT')")
    public Response<Boolean> deleteById(@PathVariable("id") Long id) {
        return new Response<>(employeeService.deleteById(id));
    }

}