package com.example.backend.service;


import com.example.backend.domain.auth.UserRole;
import com.example.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public UserRole getRoleByName(String name) {
        return roleRepository.getByName(name);
    }
}
