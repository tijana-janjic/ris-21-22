package com.example.backend.service;

import com.example.backend.domain.travel.Article;
import com.example.backend.domain.travel.CityTour;
import com.example.backend.domain.travel.Tour;
import com.example.backend.repository.travel.ArticleRepository;
import com.example.backend.repository.travel.CityTourRepository;
import com.example.backend.repository.travel.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TourService {

    private final TourRepository tourRepository;
    private final ArticleRepository articleRepository;
    private final CityTourRepository cityTourRepository;

    @Autowired
    public TourService(TourRepository tourRepository, ArticleRepository articleRepository, CityTourRepository cityTourRepository){
        this.tourRepository = tourRepository;
        this.articleRepository = articleRepository;
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

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public List<CityTour> findAllCityToursByIds(Set<Long> cityTours) {
        return cityTourRepository.findAllById(cityTours);
    }

    public List<CityTour> findAllCityToursByTourId(Set<Long> cityTours) {
        return cityTourRepository.findAllById(cityTours);
    }


    public List<Tour> getToursByAgent(String email) {
        return tourRepository.findByAgentEmail(email);
    }

    public List<Tour> getToursByGuide(String email) {
        return tourRepository.findByGuideEmail(email);
    }

    public List<Tour> getReservations(String email) {
        return tourRepository.findByClientEmailAndDeadlineAfter(email, LocalDateTime.now());
    }

    public List<Tour> getTrips(String email) {
        return tourRepository.findByClientEmailAndDeadlineBefore(email, LocalDateTime.now());
    }

    public List<Tour> getActiveTours() {
        return tourRepository.findByDeadlineAfter(LocalDateTime.now());
    }

    public List<Article> getAllArticlesByAgent(String email) {
        return articleRepository.findAllByAgentEmail(email);
    }

    public void deleteTour(Tour tour) {
        tourRepository.delete(tour);
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElseThrow();
    }

    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }
}
