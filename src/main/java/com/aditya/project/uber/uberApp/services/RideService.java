package com.aditya.project.uber.uberApp.services;

import com.aditya.project.uber.uberApp.dto.RideRequestDto;
import com.aditya.project.uber.uberApp.entities.Driver;
import com.aditya.project.uber.uberApp.entities.Ride;
import com.aditya.project.uber.uberApp.entities.RideRequest;
import com.aditya.project.uber.uberApp.entities.Rider;
import com.aditya.project.uber.uberApp.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {
    Ride getRideById(Long rideId);
    void matchWithDriver(RideRequestDto rideRequestDto);
    Ride createNewRide(RideRequest rideRequest, Driver driver);
    Ride updateRideStatus(Ride ride,RideStatus rideStatus);
    Page<Ride> getAllRideOfDriver(Driver driver, PageRequest pageRequest);
    Page<Ride> getAllRideOfRider(Rider rider, PageRequest pageRequest);
}
