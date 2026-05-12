package com.kwang43.boot.repository;

import com.kwang43.boot.domain.OaUsers;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OaUsersRepository extends JpaRepository<OaUsers, Integer>, PagingAndSortingRepository<OaUsers, Integer> {
    List<OaUsers> findByNickName(String nickName);

    OaUsers findByEmail(String email);

    OaUsers findByEmailIgnoreCase(String email);
}
