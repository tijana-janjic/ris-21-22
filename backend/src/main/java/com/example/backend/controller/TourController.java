package com.example.backend.controller;

import com.example.backend.domain.travel.Tour;
import com.example.backend.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping(value = "/tours")
public class TourController {

    @Autowired
    TourService tourService;

    @GetMapping
    public Set<Tour> getAllTours(){
        return tourService.getAllTours();
    }
}
