package com.example.backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
@Data
public class TravelogueDto implements Serializable {
    private Long id;
    private String title;
    private String text;
    private CityDto city;
    private String agentEmail;
    private String agentName;
    private String agentSurname;
    private FileDto coverImage;
}
