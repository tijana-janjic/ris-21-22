package com.example.backend.controller;

import com.example.backend.domain.person.Agent;
import com.example.backend.domain.person.Guide;
import com.example.backend.domain.travel.Article;
import com.example.backend.domain.travel.CityTour;
import com.example.backend.domain.travel.Tour;
import com.example.backend.domain.travel.TourType;
import com.example.backend.dto.*;
import com.example.backend.mappers.TourMapper;
import com.example.backend.security.TokenUtils;
import com.example.backend.service.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @GetMapping("agent/monthly-report")
    public void showAgentReport(HttpServletRequest request, HttpServletResponse response) throws JRException, IOException {
        String token = request.getCookies()[0].getValue();
        String email = tokenUtils.getEmailFromToken(token);
        System.out.println(email);
        Agent agent = accountService.getAgentByEmail(email);
        System.out.println(agent);
        // dobavljanje podataka, postavljanje vrijednosti parametar, kompajliranje
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(tourService.getToursByAgent(email));

        String path = "/home/tijanajanjic/Documents/PMF/8. semestar/NWP/projekat/ris-21-22/backend/src/main/resources/reports/agent.jrxml";

//        InputStream inputStream = this.getClass().getResourceAsStream(path);
//        JasperReport jasperReport = new JasperReport(path);
//        assert inputStream != null;
//        inputStream.close();

        // Load invoice jrxml template.
        JasperDesign jasDesign = JRXmlLoader.load(path);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasDesign);

        // Create parameters map.
        // final Map<String, Object> parameters = parameters(invoice);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("agencyName", "Paper plane travel");
        parameters.put("agentFullName", agent.getName() + " " + agent.getSurname());
        parameters.put("fixedSalary", agent.getFixedSalary());
        parameters.put("bonusPerTour", agent.getBonusPerTour());

        response.setContentType("application/pdf");

        ServletOutputStream out = response.getOutputStream();
        // Render as PDF.
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfStream(print, out);

    }

    @GetMapping("guide/monthly-report")
    public void showGuideReport(HttpServletRequest request, HttpServletResponse response) throws JRException, IOException {
        String token = request.getCookies()[0].getValue();
        String email = tokenUtils.getEmailFromToken(token);
        System.out.println(email);
        Guide guide = accountService.getGuideByEmail(email);
        System.out.println(guide);
        // dobavljanje podataka, postavljanje vrijednosti parametar, kompajliranje
        List<Tour> tours = tourService.getToursByGuide(email);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(tours);

        String path = "/home/tijanajanjic/Documents/PMF/8. semestar/NWP/projekat/ris-21-22/backend/src/main/resources/reports/guide.jrxml";

//        InputStream inputStream = this.getClass().getResourceAsStream(path);
//        JasperReport jasperReport = new JasperReport(path);
//        assert inputStream != null;
//        inputStream.close();

        // Load invoice jrxml template.
        JasperDesign jasDesign = JRXmlLoader.load(path);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasDesign);

        // Create parameters map.
        // final Map<String, Object> parameters = parameters(invoice);

        System.out.println(tours);

        double bonus = 0;
        double percentage = guide.getPercentagePerTour();
        for (Tour tour: tours ) {
            bonus += tour.getPrice();
        }
        bonus *= percentage;


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("agencyName", "Paper plane travel");
        parameters.put("guideFullName", guide.getName() + " " + guide.getSurname());
        parameters.put("fixedSalary", guide.getFixedSalary());
        parameters.put("percentagePerTour", percentage);
        parameters.put("bonus", bonus);

        response.setContentType("application/pdf");

        ServletOutputStream out = response.getOutputStream();
        // Render as PDF.
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfStream(print, out);

    }


}
