package com.example.backend.dto;

import com.example.backend.domain.utils.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
@Data
public class ClientDto implements Serializable {
    private String umcn;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private Gender gender;
    private String email;
    private String password;
}
