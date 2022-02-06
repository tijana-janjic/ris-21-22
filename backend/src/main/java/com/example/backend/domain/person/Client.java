package com.example.backend.domain.person;

import com.example.backend.domain.travel.Tour;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "client")
public class Client extends Person {

    @ManyToMany
    @JoinTable(
            name = "reservations",
            joinColumns = @JoinColumn(name = "client_email"),
            inverseJoinColumns = @JoinColumn(name = "tour_id"))
    private Set<Tour> reserved = new HashSet<>();

    public boolean addReservation(Tour tour) {
        return reserved.add(tour);
    }

    public boolean removeReservation(Tour tour) {
        return reserved.remove(tour);
    }


}