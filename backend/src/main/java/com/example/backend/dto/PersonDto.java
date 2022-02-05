package com.example.backend.dto;

import com.example.backend.domain.utils.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
@Data
public class PersonDto implements Serializable {
    private String name;
    private String surname;
    private String birthdate;
    private Gender gender;
    private String phoneNumber;
    private String email;
    private String password;
}
