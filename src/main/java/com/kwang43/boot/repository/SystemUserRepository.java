package com.kwang43.boot.repository;

import com.kwang43.boot.domain.VEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserRepository extends JpaRepository<VEmployee, Long>, PagingAndSortingRepository<VEmployee,Long> {
}
