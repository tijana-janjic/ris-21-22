package com.example.backend.service;

import com.example.backend.domain.travel.CityTour;
import com.example.backend.domain.travel.Tour;
import com.example.backend.repository.travel.CityTourRepository;
import com.example.backend.repository.travel.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TourService {

    private final TourRepository tourRepository;
    private final CityTourRepository cityTourRepository;

    @Autowired
    public TourService(TourRepository tourRepository, CityTourRepository cityTourRepository){
        this.tourRepository = tourRepository;
        this.cityTourRepository = cityTourRepository;
    }

    public Set<Tour> getAllTours() {
        return new HashSet<>(tourRepository.findAll());
    }

    public Set<CityTour> getAllCityTours() {
        return new HashSet<>(cityTourRepository.findAll());
    }

    public Tour addNewTour(Tour tour) {
        return tourRepository.save(tour);
    }

    public CityTour addNewCityTour(CityTour cityTour) {
        return cityTourRepository.save(cityTour);
    }
}
