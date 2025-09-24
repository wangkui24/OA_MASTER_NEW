package com.kwang43.boot.userdetail.impl;


import com.kwang43.boot.core.Const;
import com.kwang43.boot.core.security.CustomizeUserInfo;
import com.kwang43.boot.domain.OaUsers;
import com.kwang43.boot.repository.OaUsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
public class UserDetailWeb extends UserDetailBase{

    @Resource
    private OaUsersRepository oaUsersRepository;

    @Override
    public int index() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isMatch(String username) {
        return true;
    }

    @Override
    public User getUser(String email) {
        OaUsers oaUsers = findByEmail(email);

        Set<GrantedAuthority> authorities = getGrantedAuthoritiesByUserType(Const.TerminalType.CLIENT_WEB);
        oaUsers.getGroup().getRoles().forEach(r -> authorities.add(new SimpleGrantedAuthority(String.format("%s%s", Const.ROLE_PREFIX, r.getName()))));

        return CustomizeUserInfo.with()
                .nickName(oaUsers.getNickName())
                .password(oaUsers.getPassword())
                .authorities(authorities)
                .userId(oaUsers.getId())
                .email(oaUsers.getEmail())
                .userType(Const.UserCategory.WEB.getCode())
                .build();
    }

    private OaUsers findByEmail(String email) {
        return Optional.ofNullable(oaUsersRepository.findByEmailIgnoreCase(email))
                        .orElseThrow(() -> new UsernameNotFoundException("User [" + email + "] not exists"));
    }
}
