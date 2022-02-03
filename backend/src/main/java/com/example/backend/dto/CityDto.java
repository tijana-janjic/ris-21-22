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
public class CityDto implements Serializable {
    private Long id;
    private String name;
    private CountryDto country;
    private Set<FileDto> gallery;
}
