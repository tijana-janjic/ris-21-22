package com.example.backend.dto;

import com.example.backend.domain.travel.TourType;
import com.example.backend.domain.travel.TransportationType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Data
public class TourDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime deadline;
    private Double price;
    private Integer maxPassengers;
    private TransportationType transportationType;
    private TourType tourType;
    private String guideEmail;
    private String agentEmail;
    private FileDto coverImage;
    private Set<String> cities;
}
