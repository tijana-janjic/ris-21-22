package com.example.backend.domain.travel;

import com.example.backend.domain.location.City;
import com.example.backend.domain.person.Agent;
import com.example.backend.domain.utils.File;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "agent_email")
    private Agent agent;

    @ManyToOne(cascade = CascadeType.ALL)
    private File coverImage;

}