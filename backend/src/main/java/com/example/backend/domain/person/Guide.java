package com.example.backend.domain.person;

import com.example.backend.domain.travel.Tour;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "guide")
public class Guide extends Person {

    @Column(name = "fixedSalary", nullable = false)
    private Double fixedSalary;

    @Column(name = "percentagePerTour", nullable = false)
    private Double percentagePerTour;

    @ManyToOne
    @JoinColumn(name = "guided_trips_id")
    private Tour guidedTrips;
}