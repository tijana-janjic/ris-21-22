package com.example.backend.service;

import com.example.backend.domain.person.Agent;
import com.example.backend.domain.person.Client;
import com.example.backend.domain.person.Guide;
import com.example.backend.repository.person.AgentRepository;
import com.example.backend.repository.person.ClientRepository;
import com.example.backend.repository.person.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final GuideRepository guideRepository;
    private final AgentRepository agentRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public PersonService(GuideRepository guideRepository, AgentRepository agentRepository, ClientRepository clientRepository) {
        this.guideRepository = guideRepository;
        this.agentRepository = agentRepository;
        this.clientRepository = clientRepository;
    }

    public Guide getGuideById(String id) {
        return guideRepository.getById(id);
    }

    public Agent getAgentById(String id) {
        return agentRepository.getById(id);
    }

    public Client getClientById(String id) {
        return clientRepository.getById(id);
    }
}
