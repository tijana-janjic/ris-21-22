package com.example.backend.controller;

import com.example.backend.domain.person.Agent;
import com.example.backend.domain.travel.*;
import com.example.backend.dto.*;
import com.example.backend.mappers.TourMapper;
import com.example.backend.security.TokenUtils;
import com.example.backend.service.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping(value = "/travel")
public class TravelController {

    private final TourService tourService;
    private final ArticleService articleService;
    private final LocationService locationService;
    private final TourMapper tourMapper;
    private final ModelMapper modelMapper;
    private final TokenUtils tokenUtils;
    private final AccountService accountService;


    @Autowired
    public TravelController(TourService tourService, ArticleService articleService, LocationService locationService, PersonService personService, ModelMapper modelMapper, TokenUtils tokenUtils, AccountService accountService) {
        this.tourService = tourService;
        this.articleService = articleService;
        this.locationService = locationService;
        this.modelMapper = modelMapper;
        this.tokenUtils = tokenUtils;
        this.accountService = accountService;
        this.tourMapper = new TourMapper(locationService, personService, tourService, modelMapper);
    }

    // tour types

    @GetMapping("/tourtypes")
    public List<String> getAllTourTypes() {
        return Arrays.stream(TourType.values())
                .map(Enum::toString).toList();
    }




    // tours

    @GetMapping("/tours")
    public Set<TourDto> getAllActiveTours() {
        return tourService.getActiveTours().stream().map(tourMapper::tourToDto).collect(Collectors.toSet());
    }

    @GetMapping("agent/tours")
    public Set<TourDto> getToursByAgent(HttpServletRequest request) {
        String token = request.getCookies()[0].getValue();
        System.out.println("agent/tours " + token);
        String email = tokenUtils.getEmailFromToken(token);
        return tourService.getToursByAgent(email).stream().map(tourMapper::tourToDto).collect(Collectors.toSet());
    }

    @GetMapping("guide/tours")
    public Set<TourDto> getToursByGuide(HttpServletRequest request) {
        String token = request.getCookies()[0].getValue();
        String email = tokenUtils.getEmailFromToken(token);
        return tourService.getToursByGuide(email).stream().map(tourMapper::tourToDto).collect(Collectors.toSet());
    }

    @GetMapping("client/reservations")
    public Set<TourDto> getReservations(HttpServletRequest request) {
        String token = request.getCookies()[0].getValue();
        String email = tokenUtils.getEmailFromToken(token);
        return tourService.getReservations(email).stream().map(tourMapper::tourToDto).collect(Collectors.toSet());
    }

    @GetMapping("client/trips")
    public Set<TourDto> getTrips(HttpServletRequest request) {
        String token = request.getCookies()[0].getValue();
        String email = tokenUtils.getEmailFromToken(token);
        return tourService.getTrips(email).stream().map(tourMapper::tourToDto).collect(Collectors.toSet());
    }

    @PostMapping("/create-tour")
    public ResponseEntity<Long> addTour(@RequestBody NewTourDto tourDto, HttpServletRequest request){
        String token = request.getCookies()[0].getValue();
        String email = tokenUtils.getEmailFromToken(token);
        Tour tour = tourMapper.dtoToTour(tourDto);
        tour.setAgent(accountService.getAgentByEmail(email));
        tour = tourService.addNewTour(tour);
        return new ResponseEntity<>(tour.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-tour")
    public ResponseEntity<Long> deleteTour(@RequestParam Long id){
        Tour tour = tourService.getTourById(id);
        tourService.deleteTour(tour);
        return new ResponseEntity<>(tour.getId(), HttpStatus.OK);
    }


    // city tours


    @GetMapping("/tour/city-tours")
    public Set<CityTourDto> getCityToursByTourId(@RequestParam Long id) {
        return tourService.getTourById(id).getCityTours().stream().map(x -> modelMapper.map(x, CityTourDto.class)).collect(Collectors.toSet());
    }

    @GetMapping("/city-tours")
    public Set<CityTourDto> getAllCityTours(){
        return tourService.getAllCityTours().stream().map(x -> modelMapper.map(x, CityTourDto.class)).collect(Collectors.toSet());
    }

    @PostMapping("/create-city-tour")
    public ResponseEntity<Long> addCityTour(@RequestBody NewCityTourDto cityTourDto){
        CityTour cityTour = tourMapper.dtoToCityTour(cityTourDto);
        cityTour = tourService.addNewCityTour(cityTour);
        return new ResponseEntity<>(cityTour.getId(), HttpStatus.CREATED);
    }


    // articles

    @GetMapping("/blog")
    public Set<ArticleDto> getAllArticles() {
        List<Article> set = tourService.getAllArticles();
        return set.stream().map(tourMapper::articleToDto).collect(Collectors.toSet());
    }

    @GetMapping("agent/articles")
    public Set<ArticleDto> getAllArticlesByAgent(HttpServletRequest request){
        String token = request.getCookies()[0].getValue();
        String email = tokenUtils.getEmailFromToken(token);
        List<Article> set = tourService.getAllArticlesByAgent(email);
        return set.stream().map(tourMapper::articleToDto).collect(Collectors.toSet());
    }

    @PostMapping("/create-article")
    public ResponseEntity<Long> addArticle(@RequestBody NewArticleDto articleDto, HttpServletRequest request){
        String token = request.getCookies()[0].getValue();
        String email = tokenUtils.getEmailFromToken(token);
        Article article = articleService.addNewArticle(tourMapper.dtoToArticle(articleDto, email));
        return new ResponseEntity<>(article.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-article")
    public ResponseEntity<Long> deleteArticle(@RequestParam Long id){
        Article article = tourService.getArticleById(id);
        tourService.deleteArticle(article);
        return new ResponseEntity<>(article.getId(), HttpStatus.OK);
    }



    // location

    @GetMapping("/countries")
    public Set<CountryDto> getAllCountries(){
        return locationService.getAllCountries().stream().map(x -> modelMapper.map(x, CountryDto.class)).collect(Collectors.toSet());
    }

    @GetMapping("/cities")
    public Set<CityDto> getCitiesByCountryId(@RequestParam Long id){
        System.err.println("getCitiesByCountryId");
        return locationService.getCitiesByCountryId(id).stream().map(x -> modelMapper.map(x, CityDto.class)).collect(Collectors.toSet());
    }

    @GetMapping("/landmarks")
    public Set<LandmarkDto> getLandmarksByCityId(@RequestParam Long id){
        return locationService.getLandmarksByCityId(id).stream().map(x -> modelMapper.map(x, LandmarkDto.class)).collect(Collectors.toSet());
    }

    @GetMapping("/hotels")
    public Set<HotelDto> getHotelsByCityId(@RequestParam Long id){
        return locationService.getHotelsByCityId(id).stream().map(x -> modelMapper.map(x, HotelDto.class)).collect(Collectors.toSet());
    }




    // report

    @GetMapping("agent/tours-report")
    public ResponseEntity<byte[]> showReport(HttpServletRequest request) throws JRException, IOException {
        String token = request.getCookies()[0].getValue();
        String email = tokenUtils.getEmailFromToken(token);

        Agent agent = accountService.getAgentByEmail(email);

        // dobavljanje podataka, postavljanje vrijednosti parametar, kompajliranje
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(tourService.getToursByAgent(email));

        InputStream inputStream = this.getClass().getResourceAsStream("/resources/reports/tours.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        Map<String, Object> params = new HashMap<>();
        params.put("agencyName", "Paper plane travel");
        params.put("agentFullName", agent.getName() + " " + agent.getSurname());
        params.put("fixedSalary", agent.getFixedSalary());
        params.put("bonusPerTour", agent.getBonusPerTour());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
        assert inputStream != null;
        inputStream.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("toursReport.pdf", "toursReport.pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(JasperExportManager.exportReportToPdf(jasperPrint));
    }


}
