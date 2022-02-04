package com.example.backend.controller;


import javax.servlet.http.Cookie;
import com.example.backend.domain.auth.User;
import com.example.backend.dto.LoginRequestDto;
import com.example.backend.dto.PermissionCheckerDTO;
import com.example.backend.security.MyUserPrinciple;
import com.example.backend.security.TokenUtils;
import com.example.backend.service.AuthorizationService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/auth")
public class AccountController {


    private final AuthenticationManager authenticationManager;
    private final AuthorizationService authorizationService;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final TokenUtils tokenUtils;

    @Autowired
    public AccountController(AuthenticationManager authenticationManager,
                             AuthorizationService authorizationService,
                             UserDetailsService userDetailsService,
                             UserService userService,
                             TokenUtils tokenUtils
                             ) {
        this.authenticationManager = authenticationManager;
        this.authorizationService = authorizationService;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.tokenUtils = tokenUtils;
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

        System.out.println("authentication:  " + u.getEmail());

        MyUserPrinciple myUserPrinciple = (MyUserPrinciple) authentication.getPrincipal();
        User user = myUserPrinciple.getUser();
        String token = tokenUtils.generateToken(user.getEmail());
        getCookies(user, token).forEach(response::addCookie);

        System.out.println("token: " + token);

        return ResponseEntity.ok().build();

    }
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @GetMapping("/logout")
    public ResponseEntity logout(HttpServletResponse response) {
        getCookiesWithInvalidDates(response).forEach(response::addCookie);
        return ResponseEntity.ok().build();
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

    @PostMapping("/check-permission")
    public ResponseEntity<Boolean> checkIfUserHasPermission(@RequestBody PermissionCheckerDTO permissionCheckerDTO) {
        Boolean hasPermission = authorizationService.checkIfUserHasPermission(permissionCheckerDTO.getToken(), permissionCheckerDTO.getResource());
        return new ResponseEntity<>(hasPermission, HttpStatus.OK);
    }

    private Set<Cookie> getCookies(User user, String token) {
        Set<Cookie> cookies = new HashSet<>();

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
