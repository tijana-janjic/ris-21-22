package com.example.backend.domain.location;

import com.example.backend.domain.utils.File;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "stars", nullable = false)
    private Integer stars;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany
    private Set<File> gallery;
}