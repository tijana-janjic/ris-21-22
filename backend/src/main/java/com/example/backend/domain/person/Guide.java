package com.example.backend.domain.person;

import com.example.backend.domain.travel.Tour;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "guide")
public class Guide extends Person {

    @Column(name = "fixed_salary", nullable = false)
    private Double fixedSalary;

    @Column(name = "percentage_per_tour", nullable = false)
    private Double percentagePerTour;

    @OneToMany(mappedBy = "guide")
    private Collection<Tour> tours;

}