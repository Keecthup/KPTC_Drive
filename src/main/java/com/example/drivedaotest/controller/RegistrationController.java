package com.example.drivedaotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.drivedaotest.entity.FileUser;
import com.example.drivedaotest.storage.CustomUserDetailsService;

@RestController
public class RegistrationController {

    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public RegistrationController(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody FileUser userDto) {
        try {
            userDetailsService.registerNewUser(userDto);
            return ResponseEntity.ok("User registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
