package com.example.backend.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

public class TokenBasedAuthentication extends AbstractAuthenticationToken {

    private String token;

    public TokenBasedAuthentication(){
        super(new ArrayList<>());
    }

    public void setToken(String token){
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public UserDetails getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }
}
