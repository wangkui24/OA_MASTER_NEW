package com.kwang43.boot.repository;

import com.kwang43.boot.domain.SystemUser;
import com.kwang43.boot.domain.VEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Integer>, PagingAndSortingRepository<SystemUser, Integer> {
    List<SystemUser> findByUsername(String username);

    List<SystemUser> findByEmail(String email);
}
