package com.example.backend.dto;

import com.example.backend.domain.utils.File;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Data
public class CityTourDto implements Serializable {
    private Long id;
    private String name;
    private CityDto city;
    private HotelDto hotel;
    private Set<LandmarkDto> landmarks;
    private FileDto coverImage;
}