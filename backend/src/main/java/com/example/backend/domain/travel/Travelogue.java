package com.example.backend.domain.travel;

import com.example.backend.domain.location.City;
import com.example.backend.domain.person.Agent;
import com.example.backend.domain.utils.File;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "travelogue")
public class Travelogue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "agent_umcn")
    private Agent agent;

    @OneToMany
    private Set<File> gallery;
}