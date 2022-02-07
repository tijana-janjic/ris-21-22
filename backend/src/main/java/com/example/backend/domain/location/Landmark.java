package com.example.backend.domain.location;

import com.example.backend.domain.location.City;
import com.example.backend.domain.travel.CityTour;
import com.example.backend.domain.utils.File;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "landmark")
public class Landmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "enterance_price", nullable = false)
    private Double enterancePrice;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
}