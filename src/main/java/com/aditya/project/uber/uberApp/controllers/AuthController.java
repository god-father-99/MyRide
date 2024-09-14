package com.aditya.project.uber.uberApp.controllers;

import com.aditya.project.uber.uberApp.dto.SignupDto;
import com.aditya.project.uber.uberApp.dto.UserDto;
import com.aditya.project.uber.uberApp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/signup")
    UserDto Signup(@RequestBody SignupDto signupDto) {
        return authService.signup(signupDto);
    }
}
