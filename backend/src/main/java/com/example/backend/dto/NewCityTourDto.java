package com.example.backend.dto;

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
public class NewCityTourDto implements Serializable {
    private String name;
    private Long cityId;
    private Long hotelId;
    private Set<Long> landmarkIds;
    private FileDto coverImage;
}
