package com.kwang43.boot.repository;

import com.kwang43.boot.domain.SystemRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface SystemRoleRepository extends JpaRepository<SystemRole, Long>, PagingAndSortingRepository<SystemRole, Long> {
    Optional<SystemRole> findById(Long id);
}
