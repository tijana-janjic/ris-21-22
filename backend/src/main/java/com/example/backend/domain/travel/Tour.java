package com.example.backend.domain.travel;

import com.example.backend.domain.person.Agent;
import com.example.backend.domain.person.Client;
import com.example.backend.domain.person.Guide;
import com.example.backend.domain.utils.File;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
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

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "max_passengers", nullable = false)
    private Integer maxPassengers;

    @Enumerated(EnumType.STRING)
    @Column(name = "transportation_type", nullable = false)
    private TransportationType transportationType;

    @Enumerated(EnumType.STRING)
    @Column(name = "tour_type", nullable = false)
    private TourType tourType;

    @ManyToOne
    @JoinColumn(name = "guide_email")
    private Guide guide;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private File coverImage;

    @ManyToOne
    @JoinColumn(name = "agent_email")
    private Agent agent;

    @ManyToMany(mappedBy = "reserved")
    private Set<Client> client;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "tours_city_tours",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "city_tour_id"))
    private Set<CityTour> cityTours;

}