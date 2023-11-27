package com.kwang43.boot.repository;

import com.kwang43.boot.domain.Employee;
import com.kwang43.boot.model.dto.EmployeeDto;
import com.kwang43.boot.model.dto.EmployeeQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee>, PagingAndSortingRepository<Employee, Long> {

    @Query(nativeQuery = true, value =
            "select * from employee where dept_id = :#{deptId}",
            countQuery =
            "select count(id) from employee where dept_id = :#{deptId}")
    Page<EmployeeDto> findByDeptId(@Param("deptId") Integer deptId, Pageable pageable);

    @Query(" Select e From Employee e " +
            " Where (:#{#search.id} IS NULL OR e.id = :#{#search.id}) " +
            " AND (:#{#search.id} IS NULL OR e.id = :#{#search.id}) " +
            " AND (:#{#search.name} IS NULL OR e.name = :#{#search.name}) " +
            " AND (:#{#search.nickName} IS NULL OR e.nickName = :#{#search.nickName}) " +
            " AND (:#{#search.cellphone} IS NULL OR e.cellphone = :#{#search.cellphone}) " +
            " AND (:#{#search.email} IS NULL OR e.email = :#{#search.email}) " +
            " AND (:#{#search.gender} IS NULL OR e.gender = :#{#search.gender}) " +
            " AND (:#{#search.status} IS NULL OR e.status = :#{#search.status}) " +
            " AND (:#{#search.deptId} IS NULL OR e.deptId = :#{#search.deptId}) " +
            " AND (:#{#search.schoolId} IS NULL OR e.schoolId = :#{#search.schoolId}) ")
    Page<Employee> findEmployeeBySearchDto(@Param("search") EmployeeQueryDto search, Pageable pageable);
}
