package com.example.backend.controller;

import com.example.backend.domain.travel.CityTour;
import com.example.backend.domain.travel.Tour;
import com.example.backend.dto.CityTourDto;
import com.example.backend.dto.TourDto;
import com.example.backend.mappers.TourMapper;
import com.example.backend.service.LocationService;
import com.example.backend.service.PersonService;
import com.example.backend.service.TourService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/travel")
public class TravelController {

    private final TourService tourService;
    private final TourMapper tourMapper;

    @Autowired
    public TravelController(TourService tourService, LocationService locationService, PersonService personService, ModelMapper modelMapper) {
        this.tourService = tourService;
        this.tourMapper = new TourMapper(locationService, personService, modelMapper);
    }

    @GetMapping("/tours")
    public Set<Tour> getAllTours(){
        return new HashSet<>(tourService.getAllTours());
    }

    @GetMapping("/city-tours")
    public Set<CityTour> getAllCityTours(){
        return new HashSet<>(tourService.getAllCityTours());
    }

    @PostMapping("/create-tour")
    public ResponseEntity<Long> addTour(@RequestBody TourDto tourDto){
        Tour tour = tourService.addNewTour(tourMapper.dtoToTour(tourDto));
        return new ResponseEntity<>(tour.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/create-city-tour")
    public ResponseEntity<Long> addCityTour(@RequestBody CityTourDto cityTourDto){
        CityTour cityTour = tourService.addNewCityTour(tourMapper.dtoToCityTour(cityTourDto));
        return new ResponseEntity<>(cityTour.getId(), HttpStatus.CREATED);
    }

}
