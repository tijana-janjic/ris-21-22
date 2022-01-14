package com.example.backend.domain.person;

import com.example.backend.domain.travel.Tour;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "client")
public class Client extends Person {

    @ManyToOne
    @JoinColumn(name = "reserved_id")
    private Tour reserved;

    @ManyToOne
    @JoinColumn(name = "previous_trips_id")
    private Tour previousTrips;

}