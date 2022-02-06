package com.example.backend.controller;

import com.example.backend.domain.travel.*;
import com.example.backend.dto.*;
import com.example.backend.mappers.TourMapper;
import com.example.backend.security.TokenUtils;
import com.example.backend.service.ArticleService;
import com.example.backend.service.LocationService;
import com.example.backend.service.PersonService;
import com.example.backend.service.TourService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/travel")
public class TravelController {

    private final TourService tourService;
    private final ArticleService articleService;
    private final LocationService locationService;
    private final TourMapper tourMapper;
    private final ModelMapper modelMapper;
    private final TokenUtils tokenUtils;

    @Autowired
    public TravelController(TourService tourService, ArticleService articleService, LocationService locationService, PersonService personService, ModelMapper modelMapper, TokenUtils tokenUtils) {
        this.tourService = tourService;
        this.articleService = articleService;
        this.locationService = locationService;
        this.modelMapper = modelMapper;
        this.tokenUtils = tokenUtils;
        this.tourMapper = new TourMapper(locationService, personService, tourService, modelMapper);
    }


    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @GetMapping("/tourtypes")
    public List<String> getAllTourTypes() {
        return Arrays.stream(TourType.values())
                .map(Enum::toString).toList();
    }

    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @GetMapping("/tours")
    public Set<TourDto> getAllTours() {
        return tourService.getAllTours().stream().map(tourMapper::tourToDto).collect(Collectors.toSet());
    }

    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @GetMapping("/blog")
    public Set<ArticleDto> getAllArticles() { // prepraviti tipove
        List<Article> set = tourService.getAllArticles();
        return set.stream().map(tourMapper::articleToDto).collect(Collectors.toSet());
    }

    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @GetMapping("/tour/city-tours")
    public Set<CityTourDto> getCityToursByTourId(@RequestParam Long id) {
        return tourService.getTourById(id).getCityTours().stream().map(x -> modelMapper.map(x, CityTourDto.class)).collect(Collectors.toSet());
    }

    @GetMapping("/city-tours")
    public Set<CityTourDto> getAllCityTours(){
        return tourService.getAllCityTours().stream().map(x -> modelMapper.map(x, CityTourDto.class)).collect(Collectors.toSet());
    }

    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @GetMapping("/countries")
    public Set<CountryDto> getAllCountries(){
        return locationService.getAllCountries().stream().map(x -> modelMapper.map(x, CountryDto.class)).collect(Collectors.toSet());
    }

    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @GetMapping("/cities")
    public Set<CityDto> getCitiesByCountryId(@RequestParam Long id){
        System.err.println("getCitiesByCountryId");
        return locationService.getCitiesByCountryId(id).stream().map(x -> modelMapper.map(x, CityDto.class)).collect(Collectors.toSet());
    }

    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @GetMapping("/landmarks")
    public Set<LandmarkDto> getLandmarksByCityId(@RequestParam Long id){
        return locationService.getLandmarksByCityId(id).stream().map(x -> modelMapper.map(x, LandmarkDto.class)).collect(Collectors.toSet());
    }

    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @GetMapping("/hotels")
    public Set<HotelDto> getHotelsByCityId(@RequestParam Long id){
        return locationService.getHotelsByCityId(id).stream().map(x -> modelMapper.map(x, HotelDto.class)).collect(Collectors.toSet());
    }


    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @PostMapping("/create-tour")
    @PreAuthorize("hasPermission('POST/tour')")
    public ResponseEntity<Long> addTour(@RequestBody NewTourDto tourDto){
        Tour tour = tourService.addNewTour(tourMapper.dtoToTour(tourDto));
        return new ResponseEntity<>(tour.getId(), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @PostMapping("/create-city-tour")
    public ResponseEntity<Long> addCityTour(@RequestBody NewCityTourDto cityTourDto){
        CityTour cityTour = tourMapper.dtoToCityTour(cityTourDto);
        cityTour = tourService.addNewCityTour(cityTour);
        return new ResponseEntity<>(cityTour.getId(), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @PostMapping("/create-article")
    public ResponseEntity<Long> addArticle(@RequestBody NewArticleDto articleDto, HttpServletRequest request){
        String token = request.getCookies()[0].getValue();
        String email = tokenUtils.getEmailFromToken(token);

        Article article = articleService.addNewArticle(tourMapper.dtoToArticle(articleDto, email));

        return new ResponseEntity<>(article.getId(), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @GetMapping("/toursReport.pdf")
    @PreAuthorize("hasPermission('GET/report/tours')")
    public ResponseEntity<byte[]> showReport() throws Exception{

        // dobavljanje podataka, postavljanje vrijednosti parametar, kompajliranje
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(getAllTours());
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/tours.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        Map<String, Object> params = new HashMap<>();
        params.put("agencyName", "Paper plane travel");

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
