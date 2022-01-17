package com.example.backend.domain.person;

import com.example.backend.domain.travel.Tour;
import com.example.backend.domain.travel.Travelogue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "agent")
public class Agent extends Person{

    @Column(name = "fixed_salary", nullable = false)
    private Double fixedSalary;

    @Column(name = "bonus_per_tour", nullable = false)
    private Double bonusPerTour;

    @Column(name = "bonus_per_travelogue", nullable = false)
    private Double bonusPerTravelogue;

    @ManyToOne
    @JoinColumn(name = "created_trips_id")
    private Tour createdTrips;

    @ManyToOne
    @JoinColumn(name = "created_travelogue_id")
    private Travelogue createdTravelogue;

}