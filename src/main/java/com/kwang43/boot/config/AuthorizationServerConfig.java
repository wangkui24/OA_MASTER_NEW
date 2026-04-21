package com.kwang43.boot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private static final String CLIENT_AUTHORITY = "client";

    private static final String CLIENT_CREDENTIAL = "client_credentials";

    @Value("${resource.id:spring-boot-application}")
    private String resourceId;

    @Value("${access_token.validity.period:36000}")
    private int accessTokenValiditySeconds;

    @Value("${refresh_token.validity.period:2592000}")
    private int refreshTokenValiditySeconds;

}
