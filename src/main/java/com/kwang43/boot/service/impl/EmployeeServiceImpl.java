package com.kwang43.boot.service.impl;

import com.kwang43.boot.config.ResourceNotFoundException;
import com.kwang43.boot.config.Response;
import com.kwang43.boot.core.Const;
import com.kwang43.boot.core.MessageCode;
import com.kwang43.boot.domain.Employee;
import com.kwang43.boot.domain.VEmployee;
import com.kwang43.boot.model.dto.EmployeeDto;
import com.kwang43.boot.model.dto.EmployeeQueryDto;
import com.kwang43.boot.model.dto.PageResult;
import com.kwang43.boot.repository.EmployeeRepository;
import com.kwang43.boot.repository.VEmployeeRepository;
import com.kwang43.boot.service.EmployeeService;
import com.kwang43.boot.utils.HttpStatus;
import com.kwang43.boot.utils.PageableUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
    public Page<VEmployee> findAllEmployee(EmployeeQueryDto employeeQueryDto) {
        List<Sort.Order> orders = new ArrayList<>();
        if (employeeQueryDto.getSortField() == null) {
            orders.add(new Sort.Order(Sort.Direction.ASC, "id"));
        } else {
            orders.add(new Sort.Order(employeeQueryDto.isSortAsc() ? Sort.Direction.ASC : Sort.Direction.DESC, employeeQueryDto.getSortField()));
        }
        Pageable pageable = PageRequest.of(employeeQueryDto.getPage() == null ? Const.DEFAULT_PAGE_INDEX : employeeQueryDto.getPage(),
                employeeQueryDto.getPerPage() == null ? Const.DEFAULT_PAGE_SIZE : employeeQueryDto.getPerPage(), Sort.by(orders));
        return vEmployeeRepository.findAll(getSpecification(employeeQueryDto), pageable);
    }

    public Specification<VEmployee> getSpecification(EmployeeQueryDto employeeQueryDto) {
        return (root, criteriaQuery, criteriaBuilder) -> getQueryEmployeePredicate(employeeQueryDto, criteriaBuilder, root);
    }

    public Predicate getQueryEmployeePredicate(EmployeeQueryDto employeeQueryDto, CriteriaBuilder criteriaBuilder, Root<VEmployee> root) {
        List<Predicate> listCode = new ArrayList<>();
        addCriteriaPredicatePart(listCode, employeeQueryDto, criteriaBuilder, root);

        return criteriaBuilder.and(listCode.toArray(new Predicate[listCode.size()]));
    }

    private void addCriteriaPredicatePart(List<Predicate> listCode, EmployeeQueryDto employeeQueryDto, CriteriaBuilder criteriaBuilder, Root<VEmployee> root) {
        if (employeeQueryDto.getId() != null) {
            listCode.add(criteriaBuilder.like(root.get("id").as(String.class), "%" + employeeQueryDto.getId() + "%"));
        }
        if (!StringUtils.isEmpty(employeeQueryDto.getName())) {
            listCode.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + employeeQueryDto.getName() + "%"));
        }
        if (!StringUtils.isEmpty(employeeQueryDto.getEmail())) {
            listCode.add(criteriaBuilder.like(root.get("email").as(String.class), "%" + employeeQueryDto.getEmail() + "%"));
        }
        if (!StringUtils.isEmpty(employeeQueryDto.getMobile())) {
            listCode.add(criteriaBuilder.like(root.get("mobile").as(String.class), "%" + employeeQueryDto.getMobile() + "%"));
        }
        if (!StringUtils.isEmpty(employeeQueryDto.getDeptName())) {
            listCode.add(criteriaBuilder.like(root.get("deptName").as(String.class), "%" + employeeQueryDto.getDeptName() + "%"));
        }
        if (!StringUtils.isEmpty(employeeQueryDto.getRoleName())) {
            listCode.add(criteriaBuilder.like(root.get("roleName").as(String.class), "%" + employeeQueryDto.getRoleName() + "%"));
        }
        if (!StringUtils.isEmpty(employeeQueryDto.getStatus())) {
            listCode.add(criteriaBuilder.like(root.get("status").as(String.class), "%" + employeeQueryDto.getStatus() + "%"));
        }
        if (employeeQueryDto.getOnboardDateFrom() != null) {
            listCode.add(criteriaBuilder.like(root.get("onboardDateFrom").as(String.class), "%" + employeeQueryDto.getOnboardDateFrom() + "%"));
        }
        if (employeeQueryDto.getOnboardDateTo() != null) {
            listCode.add(criteriaBuilder.like(root.get("onboardDateTo").as(String.class), "%" + employeeQueryDto.getOnboardDateTo() + "%"));
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
