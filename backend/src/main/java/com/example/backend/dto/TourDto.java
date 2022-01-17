package com.example.backend.dto;

import com.example.backend.domain.travel.TourType;
import com.example.backend.domain.travel.TransportationType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@Data
public class TourDto implements Serializable {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime deadline;
    private Double price;
    private Integer maxPassengers;
    private Integer currPassengers;
    private TransportationType transportationType;
    private TourType tourType;
    private String guideId;
    private Set<CityTourDto> cityTours;
}
