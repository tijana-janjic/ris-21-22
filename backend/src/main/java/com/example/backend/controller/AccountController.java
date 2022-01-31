package com.example.backend.controller;

import com.example.backend.domain.auth.User;
import com.example.backend.domain.person.Client;
import com.example.backend.domain.person.Person;
import com.example.backend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    private final PersonService personService;

    @Autowired
    public AccountController(PersonService personService) {
        this.personService = personService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String email, @RequestParam String password){
        System.out.println("login: pogodjen sam!");

        System.out.println("parametri: " + email + ", " + password);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
