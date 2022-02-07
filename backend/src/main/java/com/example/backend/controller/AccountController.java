package com.example.backend.controller;


import com.example.backend.domain.auth.Role;
import com.example.backend.domain.auth.User;
import com.example.backend.domain.person.Agent;
import com.example.backend.domain.person.Client;
import com.example.backend.domain.person.Guide;
import com.example.backend.domain.travel.Tour;
import com.example.backend.dto.LoginRequestDto;
import com.example.backend.dto.PersonDto;
import com.example.backend.security.AuthorizationService;
import com.example.backend.security.MyUserPrinciple;
import com.example.backend.security.TokenUtils;
import com.example.backend.service.AccountService;
import com.example.backend.service.RoleService;
import com.example.backend.service.TourService;
import com.example.backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/auth")
public class AccountController {

    private final AuthenticationManager authenticationManager;
    private final AuthorizationService authorizationService;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final RoleService roleService;
    private final AccountService accountService;
    private final TourService tourService;
    private final ModelMapper modelMapper;
    private final TokenUtils tokenUtils;

    @Autowired
    public AccountController(AuthenticationManager authenticationManager,
                             AuthorizationService authorizationService,
                             UserDetailsService userDetailsService,
                             UserService userService,
                             TourService tourService, TokenUtils tokenUtils,
                             RoleService roleService,
                             AccountService accountService,
                             ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.authorizationService = authorizationService;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.tourService = tourService;
        this.tokenUtils = tokenUtils;
        this.roleService = roleService;
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }

    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @PostMapping("/login")
    public ResponseEntity login(
            @RequestBody LoginRequestDto dto, HttpServletResponse response){

        System.out.println("login: pogodjen sam!");
        System.out.println("parametri: " + dto.getEmail() + ", " + dto.getPassword());

        User u = userService.findByEmail(dto.getEmail()).orElseThrow(() -> new BadCredentialsException("Wrong password!"));

        UsernamePasswordAuthenticationToken temp = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        Authentication authentication = authenticationManager.authenticate(temp);


        MyUserPrinciple myUserPrinciple = (MyUserPrinciple) authentication.getPrincipal();
        User user = myUserPrinciple.getUser();
        String token = tokenUtils.generateToken(user.getEmail());
        getCookies(user, token).forEach(response::addCookie);

        myUserPrinciple.getAuthorities().forEach(System.err::println);

        System.out.println("token: " + token);

        return ResponseEntity.ok().build();

    }

    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @PostMapping("/register")
    public ResponseEntity register(
            @RequestBody PersonDto dto){

        System.out.println("register: pogodjen sam!");

        if(userService.findByEmail(dto.getEmail()).isPresent())
            throw new BadCredentialsException("Already used email!");

        Role role = roleService.getRoleByName("client");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole(role);

        Client client = new Client();
        client.setEmail(dto.getEmail());
        client.setName(dto.getName());
        client.setSurname(dto.getSurname());
        client.setBirthdate(LocalDate.parse(dto.getBirthdate().split("T")[0]));
        client.setGender(dto.getGender());
        client.setPhoneNumber(dto.getPhoneNumber());

        userService.saveUser(user);
        accountService.saveClient(client);

        return ResponseEntity.ok().build();

    }

    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody PersonDto dto, HttpServletRequest request){

        System.out.println("update: pogodjen sam!");

        String role = request.getCookies()[1].getValue();
        String token = request.getCookies()[0].getValue();
        String email = tokenUtils.getEmailFromToken(token);


        User user = userService.findByEmail(email).orElseThrow();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(dto.getPassword()));

        if (role.equals("client")) {
            Client client = accountService.getClientByEmail(email);
            client.setName(dto.getName());
            client.setSurname(dto.getSurname());
            client.setBirthdate(LocalDate.parse(dto.getBirthdate().split("T")[0]));
            client.setGender(dto.getGender());
            client.setPhoneNumber(dto.getPhoneNumber());
            accountService.saveClient(client);
        }

        if (role.equals("agent")) {
            Agent agent = accountService.getAgentByEmail(email);
            agent.setName(dto.getName());
            agent.setSurname(dto.getSurname());
            agent.setBirthdate(LocalDate.parse(dto.getBirthdate().split("T")[0]));
            agent.setGender(dto.getGender());
            agent.setPhoneNumber(dto.getPhoneNumber());
            accountService.saveAgent(agent);
        }

        if (role.equals("guide")) {
            Guide guide = accountService.getGuideByEmail(email);
            guide.setName(dto.getName());
            guide.setSurname(dto.getSurname());
            guide.setBirthdate(LocalDate.parse(dto.getBirthdate().split("T")[0]));
            guide.setGender(dto.getGender());
            guide.setPhoneNumber(dto.getPhoneNumber());
            accountService.saveGuide(guide);
        }
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }


    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @GetMapping("/logout")
    public ResponseEntity logout(HttpServletResponse response) {
        getCookiesWithInvalidDates(response).forEach(response::addCookie);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @PutMapping("/reserve")
    public ResponseEntity reserve(@RequestParam Long id, HttpServletRequest request) {

        Tour tour = tourService.getTourById(id);

        String token = request.getCookies()[0].getValue();
        String email = tokenUtils.getEmailFromToken(token);

        Client client = accountService.getClientByEmail(email);
        client.addReservation(tour);

        accountService.saveClient(client);

        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @PutMapping("/cancel")
    public ResponseEntity cancelReservation(@RequestParam Long id, HttpServletRequest request) {

        Tour tour = tourService.getTourById(id);

        String token = request.getCookies()[0].getValue();
        String email = tokenUtils.getEmailFromToken(token);

        Client client = accountService.getClientByEmail(email);
        client.removeReservation(tour);

        accountService.saveClient(client);

        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @GetMapping("/account")
    public ResponseEntity<PersonDto> getAccount(HttpServletRequest request,
                                     HttpServletResponse response) {

        System.err.println("\t\thiii");

        String token = request.getCookies()[0].getValue();
        String role = request.getCookies()[1].getValue();
        String email = tokenUtils.getEmailFromToken(token);


        System.err.println("token\t\t" +token);
        System.err.println("\t\t\t decoded: "+email);
        System.err.println("role \t\t" +role);

        PersonDto person;

        switch (role) {
            case "client" -> person = modelMapper.map(accountService.getClientByEmail(email), PersonDto.class);
            case "agent" -> person = modelMapper.map(accountService.getAgentByEmail(email), PersonDto.class);
            case "guide" -> person = modelMapper.map(accountService.getGuideByEmail(email), PersonDto.class);
            default -> { return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); }
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @GetMapping("/guides")
    public Set<PersonDto> getAllGuides(){
        return accountService.getAllGuides().stream().map(x -> modelMapper.map(x, PersonDto.class)).collect(Collectors.toSet());
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> checkIfUserExists(HttpServletRequest request, @RequestParam String email) {
        if (email != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            if (userDetails != null) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

    private ArrayList<Cookie> getCookies(User user, String token) {
        ArrayList<Cookie> cookies = new ArrayList<>();

        Cookie tokenCookie = new Cookie("token", token);
        tokenCookie.setMaxAge(24 * 60 * 60 * 1000);
        tokenCookie.setDomain("localhost");
        tokenCookie.setPath("/");
        tokenCookie.setHttpOnly(true);
        cookies.add(tokenCookie);

        Cookie roleCookie = new Cookie("role", user.getRole().getName());
        roleCookie.setMaxAge(24 * 60 * 60 * 1000);
        roleCookie.setDomain("localhost");
        roleCookie.setPath("/");
        roleCookie.setHttpOnly(false);
        cookies.add(roleCookie);

        return cookies;
    }

    private Set<Cookie> getCookiesWithInvalidDates(HttpServletResponse response) {
        Set<Cookie> cookies = new HashSet<>();

        Cookie tokenCookie = new Cookie("token", null);
        tokenCookie.setMaxAge(0);
        tokenCookie.setPath("/");
        tokenCookie.setDomain("localhost");
        tokenCookie.setHttpOnly(true);
        cookies.add(tokenCookie);

        Cookie roleCookie = new Cookie("role", null);
        roleCookie.setMaxAge(0);
        roleCookie.setPath("/");
        roleCookie.setDomain("localhost");
        cookies.add(roleCookie);

        return cookies;
    }

}
