package com.example.backend.domain.person;

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

    @Column(name = "bonus_per_article", nullable = false)
    private Double bonusPerArticle;

    @Override
    public String toString() {
        return "Agent{" +
                "fixedSalary=" + fixedSalary +
                ", bonusPerTour=" + bonusPerTour +
                ", bonusPerArticle=" + bonusPerArticle +
                ", person= " + super.toString() +
                '}';
    }
}