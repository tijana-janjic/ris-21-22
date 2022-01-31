package com.example.backend.dto;

import com.example.backend.domain.utils.Gender;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ClientDto implements Serializable {
    private final String umcn;
    private final String name;
    private final String surname;
    private final LocalDate birthdate;
    private final Gender gender;
    private final String email;
    private final String password;
}
