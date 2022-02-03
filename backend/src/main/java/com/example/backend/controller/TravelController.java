package com.example.backend.controller;

import com.example.backend.domain.travel.CityTour;
import com.example.backend.domain.travel.Tour;
import com.example.backend.dto.CityTourDto;
import com.example.backend.dto.TourDto;
import com.example.backend.mappers.TourMapper;
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
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/travel")
public class TravelController {

    private final TourService tourService;
    private final TourMapper tourMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public TravelController(TourService tourService, LocationService locationService, PersonService personService, ModelMapper modelMapper) {
        this.tourService = tourService;
        this.modelMapper = modelMapper;
        this.tourMapper = new TourMapper(locationService, personService, modelMapper);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/tours")
    public Set<TourDto> getAllTours() {
        return tourService.getAllTours().stream().map(tourMapper::tourToDto).collect(Collectors.toSet());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/tour/city-tours")
    public Set<CityTourDto> getCityToursByTourId(@RequestParam Long id) {
        return tourService.getTourById(id).getCityTours().stream().map(x -> modelMapper.map(x, CityTourDto.class)).collect(Collectors.toSet());
    }

    @GetMapping("/city-tours")
    public Set<CityTour> getAllCityTours(){
        return new HashSet<>(tourService.getAllCityTours());
    }

    @CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/toursReport.pdf")
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
