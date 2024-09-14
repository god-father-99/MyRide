package com.aditya.project.uber.uberApp.services;

import com.aditya.project.uber.uberApp.dto.RideDto;
import com.aditya.project.uber.uberApp.dto.RideRequestDto;
import com.aditya.project.uber.uberApp.dto.RiderDto;
import com.aditya.project.uber.uberApp.entities.Rider;
import com.aditya.project.uber.uberApp.entities.User;

import java.util.List;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto rideRequestDto);
    RideDto cancelRide(Long rideId);
    RiderDto rateDriver(Long rideId,Integer rating);
    RiderDto getMyProfile();
    List<RideDto> getAllMyRides();
    Rider createNewRider(User user);
    Rider getCurrentRider();
}
