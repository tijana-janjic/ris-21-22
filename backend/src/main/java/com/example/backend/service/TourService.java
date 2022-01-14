package com.example.backend.service;

import com.example.backend.domain.travel.Tour;
import com.example.backend.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.Set;

@Service
public class TourService {

    @Autowired
    TourRepository tourRepository;

    @GetMapping
    public Set<Tour> getAllTours() {
        Set<Tour> set = new HashSet<>(tourRepository.findAll());
        System.out.println("ss "+set);
        return set;
    }
}
