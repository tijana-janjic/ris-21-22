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
import java.util.Optional;

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

    public Optional<City> getCityById(Long id) {
        return cityRepository.findById(id);
    }
    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id);
    }

    public List<Landmark> getLandmarksByIds(List<Long> ids){
        return landmarkRepository.findAllById(ids);
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public List<City> getCitiesByCountryId(Long id){
        return cityRepository.findAllByCountryId(id);
    }

    public List<Hotel> getHotelsByCityId(Long id){
        return hotelRepository.getByCityId(id);
    }

    public List<Landmark> getLandmarksByCityId(Long id){
        return landmarkRepository.getLandmarksByCityId(id);
    }

}
