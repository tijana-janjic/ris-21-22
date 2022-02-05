package com.example.backend.mappers;

import com.example.backend.domain.location.City;
import com.example.backend.domain.location.Hotel;
import com.example.backend.domain.location.Landmark;
import com.example.backend.domain.person.Agent;
import com.example.backend.domain.person.Guide;
import com.example.backend.domain.travel.CityTour;
import com.example.backend.domain.travel.Tour;
import com.example.backend.domain.travel.Travelogue;
import com.example.backend.dto.*;
import com.example.backend.repository.travel.CityTourRepository;
import com.example.backend.service.LocationService;
import com.example.backend.service.PersonService;
import com.example.backend.service.TourService;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TourMapper {

    private final LocationService locationService;
    private final PersonService personService;
    private final TourService tourService;
    private final ModelMapper modelMapper;

    public TourMapper(LocationService locationService, PersonService personService, TourService tourService, ModelMapper modelMapper) {
        this.locationService = locationService;
        this.personService = personService;
        this.tourService = tourService;
        this.modelMapper = modelMapper;
    }

    public Tour dtoToTour(TourDto dto){
        Tour tour = modelMapper.map(dto, Tour.class);
        Guide guide = personService.getGuideById(dto.getGuideEmail());
        tour.setGuide(guide);
//        tour.setCityTours(dto.getCityTours().stream().map(this::dtoToCityTour).collect(Collectors.toSet()));
        return tour;
    }

    public Tour dtoToTour(NewTourDto dto){
        Tour tour = modelMapper.map(dto, Tour.class);
        Guide guide = personService.getGuideById(dto.getGuideEmail());

        tour.setGuide(guide);

        List<CityTour> cityTours = tourService.findAllCityToursByIds(dto.getCityTours());
        tour.setCityTours(new HashSet<>(cityTours));



        return tour;
    }


    public CityTour dtoToCityTour(CityTourDto dto){
        CityTour cityTour = modelMapper.map(dto, CityTour.class);
        City city = locationService.getCityById(dto.getId());
        Hotel hotel = locationService.getHotelById(dto.getHotel().getId());
        Set<Landmark> landmarks = new HashSet<>(
                locationService.getLandmarksByIds(
                        dto.getLandmarks().stream().map(LandmarkDto::getId).toList()));
        cityTour.setCity(city);
        cityTour.setHotel(hotel);
        cityTour.setLandmarks(landmarks);
        return cityTour;
    }

    public TourDto tourToDto(Tour tour){
        return modelMapper.map(tour, TourDto.class );
    }

    public TravelogueDto travelogueToDto(Travelogue travelogue) {
        TravelogueDto dto = modelMapper.map(travelogue, TravelogueDto.class );
        Agent a = travelogue.getAgent();
        dto.setAgentName(a.getName());
        dto.setAgentSurname(a.getSurname());
        dto.setAgentEmail(a.getEmail());
        System.err.println(a.getName() +" "+a.getSurname()+" "+a.getEmail());
        return dto;
    }
}
