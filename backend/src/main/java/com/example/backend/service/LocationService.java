package com.example.backend.service;

import com.example.backend.domain.location.City;
import com.example.backend.domain.location.Country;
import com.example.backend.domain.location.Hotel;
import com.example.backend.domain.location.Landmark;
import com.example.backend.repository.location.CityRepository;
import com.example.backend.repository.location.CountryRepository;
import com.example.backend.repository.location.HotelRepository;
import com.example.backend.repository.location.LandmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final HotelRepository hotelRepository;
    private final LandmarkRepository landmarkRepository;

    @Autowired
    public LocationService(CountryRepository countryRepository,
                           CityRepository cityRepository,
                           HotelRepository hotelRepository,
                           LandmarkRepository landmarkRepository) {
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.hotelRepository = hotelRepository;
        this.landmarkRepository = landmarkRepository;
    }

    public Country getCountryById(Long id){
        return countryRepository.getById(id);
    }

    public City getCityById(Long id){
        return cityRepository.getById(id);
    }

    public Hotel getHotelById(Long id){
        return hotelRepository.getById(id);
    }

    public List<Landmark> getLandmarksByIds(List<Long> ids){
        return landmarkRepository.findAllById(ids);
    }

}
