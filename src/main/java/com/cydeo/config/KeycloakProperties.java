package com.cydeo.config;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class KeycloakProperties {  // by using this class we can use the fields anywhere, ex: getRealm() ... access to this property anywhere in the app

    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;
    @Value("${keycloak.resource}")
    private String clientId;
    @Value("${keycloak.credentials.secret}")
    private String clientSecret;
    @Value("${master.user}")
    private String masterUser;
    @Value("${master.user.password}")
    private String masterUserPswd;
    @Value("${master.realm}")
    private String masterRealm;
    @Value("${master.client}")
    private String masterClient;

}