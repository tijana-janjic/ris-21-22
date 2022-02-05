package com.example.backend.service;

import com.example.backend.domain.travel.CityTour;
import com.example.backend.domain.travel.Tour;
import com.example.backend.domain.travel.Travelogue;
import com.example.backend.repository.TravelogueRepository;
import com.example.backend.repository.travel.CityTourRepository;
import com.example.backend.repository.travel.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TourService {

    private final TourRepository tourRepository;
    private final TravelogueRepository travelogueRepository;
    private final CityTourRepository cityTourRepository;

    @Autowired
    public TourService(TourRepository tourRepository, TravelogueRepository travelogueRepository, CityTourRepository cityTourRepository){
        this.tourRepository = tourRepository;
        this.travelogueRepository = travelogueRepository;
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

    public void downloadReport(){

    }

    public Tour getTourById(Long id) {
        return tourRepository.getById(id);
    }

    public List<Travelogue> getAllTravelogues() {
        return travelogueRepository.findAll();
    }

    public List<CityTour> findAllCityToursByIds(Set<Long> cityTours) {
        return cityTourRepository.findAllById(cityTours);
    }
}
