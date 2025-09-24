package com.kwang43.boot.userdetail.impl;

import com.kwang43.boot.core.Const;
import com.kwang43.boot.userdetail.UserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public abstract class UserDetailBase implements UserDetail {

    @Override
    public int index() {
        return 0;
    }

    @Override
    public boolean isMatch(String username) {
        return supportedUserPrefix().stream().anyMatch(userPrefix -> username.startsWith(userPrefix.getCode()));
    }

    protected List<Const.UserPrefix> supportedUserPrefix() {
        return Collections.emptyList();
    }

    protected Set<GrantedAuthority> getGrantedAuthoritiesByUserType(Const.TerminalType terminalType) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        Stream.of(terminalType.getDefaultRoles()).forEach(r -> authorities.add(new SimpleGrantedAuthority(String.format("%s%s", Const.ROLE_PREFIX, r))));
        return authorities;
    }
}
