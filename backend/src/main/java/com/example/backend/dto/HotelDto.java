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
public class HotelDto implements Serializable {
    private Long id;
    private String name;
    private Integer stars;
    private Long cityId;
    private Set<FileDto> gallery;
}
