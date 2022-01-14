package com.example.backend.domain.person;

import com.example.backend.domain.utils.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class Person {
    // Unique Master Citizen Number - umcn - jmbg
    @Id
    @Column(name = "umcn", nullable = false)
    private String umcn;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "gender", nullable = false)
    private Gender gender;
}