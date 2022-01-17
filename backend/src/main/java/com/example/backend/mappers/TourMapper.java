package com.example.backend.mappers;

import com.example.backend.domain.location.City;
import com.example.backend.domain.location.Hotel;
import com.example.backend.domain.location.Landmark;
import com.example.backend.domain.person.Guide;
import com.example.backend.domain.travel.CityTour;
import com.example.backend.domain.travel.Tour;
import com.example.backend.dto.CityTourDto;
import com.example.backend.dto.TourDto;
import com.example.backend.service.LocationService;
import com.example.backend.service.PersonService;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TourMapper {

    private final LocationService locationService;
    private final PersonService personService;
    private final ModelMapper modelMapper;

    public TourMapper(LocationService locationService, PersonService personService, ModelMapper modelMapper) {
        this.locationService = locationService;
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    public Tour dtoToTour(TourDto dto){
        Tour tour = modelMapper.map(dto, Tour.class);
        Guide guide = personService.getGuideById(dto.getGuideId());
        tour.setGuide(guide);
        tour.setCityTours(dto.getCityTours().stream().map(this::dtoToCityTour).collect(Collectors.toSet()));
        return tour;
    }

    public CityTour dtoToCityTour(CityTourDto dto){
        CityTour cityTour = modelMapper.map(dto, CityTour.class);
        City city = locationService.getCityById(dto.getCityId());
        Hotel hotel = locationService.getHotelById(dto.getHotelId());
        Set<Landmark> landmarks = new HashSet<>(
                locationService.getLandmarksByIds(
                        dto.getLandmarkIds().stream().toList()));
        cityTour.setCity(city);
        cityTour.setHotel(hotel);
        cityTour.setIncluded(dto.getIncluded());
        cityTour.setLandmarks(landmarks);
        return cityTour;
    }
}
