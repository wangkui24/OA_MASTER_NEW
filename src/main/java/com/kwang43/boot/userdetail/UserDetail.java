package com.kwang43.boot.userdetail;

import org.springframework.security.core.userdetails.User;

public interface UserDetail {
    int index();

    boolean isMatch(final String username);

    User getUser(final String username);
}
