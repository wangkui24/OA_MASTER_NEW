package com.kwang43.boot.repository;

import com.kwang43.boot.domain.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LeaveRepository extends JpaRepository<Leave, Long>, PagingAndSortingRepository<Leave,Long> {
}
