package com.example.backend.repository.person;

import com.example.backend.domain.person.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, String> {
    Agent getByEmail(String email);
}