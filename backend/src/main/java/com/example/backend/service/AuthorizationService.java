package com.example.backend.service;

import com.example.backend.domain.auth.RoleRepository;
import com.example.backend.domain.auth.User;
import com.example.backend.domain.auth.UserRole;
import com.example.backend.repository.UserRepository;
import com.example.backend.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenUtils tokenUtils;

    public Boolean checkIfUserHasPermission(String token, String resource) {
        if (token == null || !tokenUtils.isTokenValid(token)) {
            return false;
        }
        String email = tokenUtils.getEmailFromToken(token);

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User with email + " + email + "has not been found!")
        );
        UserRole role = user.getRole();

        return roleRepository.checkIfRoleHasPermission(role.getName(), resource) == 1;
    }
}
