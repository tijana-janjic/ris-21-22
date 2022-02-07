package com.example.backend.service;

import com.example.backend.domain.person.Agent;
import com.example.backend.domain.person.Client;
import com.example.backend.domain.person.Guide;
import com.example.backend.repository.person.AgentRepository;
import com.example.backend.repository.person.ClientRepository;
import com.example.backend.repository.person.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final ClientRepository clientRepository;
    private final AgentRepository agentRepository;
    private final GuideRepository guideRepository;

    @Autowired
    public AccountService(ClientRepository clientRepository, AgentRepository agentRepository, GuideRepository guideRepository) {
        this.clientRepository = clientRepository;
        this.agentRepository = agentRepository;
        this.guideRepository = guideRepository;
    }


    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    public Client getClientByEmail(String email) {
        return clientRepository.getByEmail(email);
    }

    public Agent getAgentByEmail(String email) {
        return agentRepository.getByEmail(email);
    }

    public List<Guide> getAllGuides() {
        return guideRepository.findAll();
    }

    public Guide getGuideByEmail(String email) {
        return guideRepository.getById(email);
    }

    public void saveAgent(Agent agent) {
        agentRepository.save(agent);
    }

    public void saveGuide(Guide guide) {
        guideRepository.save(guide);
    }
}
