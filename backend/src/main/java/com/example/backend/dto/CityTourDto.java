package com.example.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Data
public class CityTourDto implements Serializable {
    private Boolean included;
    private Long cityId;
    private Long hotelId;
    private Set<Long> landmarkIds;
}
