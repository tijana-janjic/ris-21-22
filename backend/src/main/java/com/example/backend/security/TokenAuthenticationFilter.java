package com.example.backend.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenUtils tokenUtils;

    public TokenAuthenticationFilter(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = tokenUtils.getToken(request);

        if (token == null)
            System.err.println("token je null! ");
        if (token != null && tokenUtils.isTokenValid(token)) {

            String email = tokenUtils.getEmailFromToken(token);

            if (email != null) {
                TokenBasedAuthentication authentication = new TokenBasedAuthentication();
                authentication.setToken(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            System.err.println("poslao: " + email);
        }
        System.err.println("token je validan! ");
        chain.doFilter(request, response);
    }
}
