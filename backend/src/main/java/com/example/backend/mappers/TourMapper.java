package com.example.backend.mappers;

import com.example.backend.domain.location.City;
import com.example.backend.domain.location.Hotel;
import com.example.backend.domain.location.Landmark;
import com.example.backend.domain.person.Agent;
import com.example.backend.domain.person.Guide;
import com.example.backend.domain.travel.Article;
import com.example.backend.domain.travel.CityTour;
import com.example.backend.domain.travel.Tour;
import com.example.backend.domain.utils.File;
import com.example.backend.dto.*;
import com.example.backend.service.LocationService;
import com.example.backend.service.PersonService;
import com.example.backend.service.TourService;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

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


    public CityTour dtoToCityTour(NewCityTourDto dto){

        CityTour cityTour = modelMapper.map(dto, CityTour.class);

        City city = locationService.getCityById(dto.getCityId()).orElseThrow(NoSuchElementException::new);
        Hotel hotel = locationService.getHotelById(dto.getHotelId()).orElseThrow(NoSuchElementException::new);;
        Set<Landmark> landmarks = new HashSet<>(
                locationService.getLandmarksByIds(dto.getLandmarkIds().stream().toList()));
        File file = cityTour.getCoverImage();

        cityTour.setName(dto.getName());
        cityTour.setCity(city);
        cityTour.setHotel(hotel);
        cityTour.setLandmarks(landmarks);
        cityTour.setCoverImage(file);
        System.err.println("\t\t\t id ture "+ cityTour);
        return cityTour;
    }

    public TourDto tourToDto(Tour tour){
        TourDto dto = modelMapper.map(tour, TourDto.class );
        Set<String> cities = tourService.getTourById(tour.getId())
                .getCityTours().stream().map(x -> x.getCity().getName()).collect(Collectors.toSet());
//        tour.setCityTours(dto.getCityTours().stream().map(this::dtoToCityTour).collect(Collectors.toSet()));
        dto.setCities(cities);
        return dto;
    }

    public ArticleDto articleToDto(Article article) {
        ArticleDto dto = modelMapper.map(article, ArticleDto.class );
        Agent a = article.getAgent();
        dto.setAgentName(a.getName());
        dto.setAgentSurname(a.getSurname());
        dto.setAgentEmail(a.getEmail());
        System.err.println(a.getName() +" "+a.getSurname()+" "+a.getEmail());
        return dto;
    }

    public Article dtoToArticle(NewArticleDto dto, String agentEmail) {

        Article article = modelMapper.map(dto, Article.class);

        File file = article.getCoverImage();
        Agent agent = personService.getAgentById(agentEmail);
        City city = locationService.getCityById(dto.getCityId()).orElseThrow();

        article.setCoverImage(file);
        article.setCity(city);
        article.setAgent(agent);
        return article;
    }
}
