package com.kwang43.boot.core.security;

import lombok.Builder;
import lombok.Singular;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomizeUserInfo extends User {

    private static final long serialVersionUID = 2817086779781112805L;

    Long userId;
    String email;
    String userType;

    @Builder(builderMethodName = "with")
    public CustomizeUserInfo(String nickName, String password, @Singular Collection<? extends GrantedAuthority> authorities,
                         Long userId, String email, String userType) {
        super(nickName, password, authorities);
        this.userId = userId;
        this.email = email;
        this.userType = userType;
    }
}
