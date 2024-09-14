package com.aditya.project.uber.uberApp.services.impl;

import com.aditya.project.uber.uberApp.dto.DriverDto;
import com.aditya.project.uber.uberApp.dto.SignupDto;
import com.aditya.project.uber.uberApp.dto.UserDto;
import com.aditya.project.uber.uberApp.entities.User;
import com.aditya.project.uber.uberApp.entities.enums.Role;
import com.aditya.project.uber.uberApp.exceptions.RuntimeConflictException;
import com.aditya.project.uber.uberApp.repositories.UserRepository;
import com.aditya.project.uber.uberApp.services.AuthService;
import com.aditya.project.uber.uberApp.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository UserRepository;
    private final RiderService RiderService;
    private final RiderServiceImpl riderServiceImpl;
    private final UserRepository userRepository;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Transactional
    @Override
    public UserDto signup(SignupDto signupDto) {
        User user=userRepository.findByEmail(signupDto
                                 .getEmail())
                                 .orElse(null);
        if(user!=null)
            throw new RuntimeConflictException("Cannot signup user exists with email: " + signupDto.getEmail());
        //create user related entities
        User mappedUser=modelMapper.map(signupDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser=userRepository.save(mappedUser);
        riderServiceImpl.createNewRider(savedUser);
        //TODO add wallet related service here
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}
