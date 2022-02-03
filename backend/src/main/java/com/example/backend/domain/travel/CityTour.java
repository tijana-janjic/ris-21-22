package com.example.backend.domain.travel;

import com.example.backend.domain.location.City;
import com.example.backend.domain.location.Hotel;
import com.example.backend.domain.location.Landmark;
import com.example.backend.domain.utils.File;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "city_tour")
public class CityTour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ToString.Exclude
    @OneToMany
    private Set<Landmark> landmarks = new java.util.LinkedHashSet<>();

    @ToString.Exclude
    @OneToMany
    private Set<Landmark> facultyLandmarks = new java.util.LinkedHashSet<>();

    @ManyToMany
    private Set<Tour> tours = new java.util.LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    private File coverImage;


}