package com.kwang43.boot.controller;

import com.kwang43.boot.config.Response;
import com.kwang43.boot.core.Const;
import com.kwang43.boot.domain.Employee;
import com.kwang43.boot.domain.VEmployee;
import com.kwang43.boot.model.dto.EmployeeDto;
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


    /*
    @GetMapping("")
    @ApiOperation(value="获取员工列表", notes="")
    public Resource<Page<VEmployee>>findEmployees(@RequestParam(value = "page", defaultValue = Const.DEFAULT_PAGE_INDEX) int page,
                                                  @RequestParam(value = "size", defaultValue = Const.DEFAULT_PAGE_SIZE) int size,
                                                  @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                                  @RequestParam(value = "sortOrder", defaultValue = "asc") String sortOrder) {
        return new Resource<>(employeeService.findAllEmployee(page,size,sortField,sortOrder));
    }
    */

    @GetMapping("")
    @ApiOperation(value="获取员工列表", notes="")
    public Response<Page<VEmployee>>findEmployees(@RequestParam(value = "page", defaultValue = Const.DEFAULT_PAGE_INDEX) int page,
                                         @RequestParam(value = "size", defaultValue = Const.DEFAULT_PAGE_SIZE) int size,
                                         @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                         @RequestParam(value = "sortOrder", defaultValue = "asc") String sortOrder) {
        return employeeService.findAllEmployee(page,size,sortField,sortOrder);
    }

//    @GetMapping(path = "/{id}")
//    @ApiOperation(value="按id查询员工信息", notes="")
//    public Response<VEmployee>findEmployeeById(@PathVariable("id") Long id) {
//        return new Response<>(employeeService.findById(id));
//    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value="按id查询员工信息", notes="")
    public Response<VEmployee> findEmployeeById(@PathVariable("id") Long id) {
        return employeeService.findEmployeeById(id);
    }


    /**
     * 注意:记得添加@RequestBody注解,否则前端传递来的json数据无法被封装到User中!
     */
    @PostMapping("")
    @ApiOperation(value="添加员工", notes="")
    public Response<Boolean> addEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.saveEmployee(employeeDto);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value="按id删除员工", notes="")
    public Response<Boolean> deleteById(@PathVariable("id") Long id) {
        return employeeService.deleteById(id);
    }

}