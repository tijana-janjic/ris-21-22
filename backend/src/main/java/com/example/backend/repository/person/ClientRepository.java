package com.example.backend.repository.person;

import com.example.backend.domain.person.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
    Client getByEmail(String email);
}