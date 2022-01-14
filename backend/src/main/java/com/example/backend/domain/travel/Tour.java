package com.example.backend.domain.travel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tour")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "startDate", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "endDate", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "maxPassengers", nullable = false)
    private Integer maxPassengers;

    @Column(name = "currPassengers", nullable = false)
    private Integer currPassengers;

    @Column(name = "transportationType", nullable = false)
    private TransportationType transportationType;

}