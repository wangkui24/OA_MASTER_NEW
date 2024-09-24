package com.kwang43.boot.repository;

import com.kwang43.boot.domain.OaRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface SystemRoleRepository extends JpaRepository<OaRoles, Long>, PagingAndSortingRepository<OaRoles, Long> {
    Optional<OaRoles> findById(Long id);
}
