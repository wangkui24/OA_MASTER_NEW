package com.kwang43.boot.repository;

import com.kwang43.boot.domain.VEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VEmployeeRepository extends JpaRepository<VEmployee, Long>, JpaSpecificationExecutor<VEmployee> {

    List<VEmployee> findByCellphone(String cellphone);

    List<VEmployee> findByEmail(String email);
}