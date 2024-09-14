package com.aditya.project.uber.uberApp.services;

import com.aditya.project.uber.uberApp.dto.RideRequestDto;
import com.aditya.project.uber.uberApp.entities.Driver;
import com.aditya.project.uber.uberApp.entities.Ride;
import com.aditya.project.uber.uberApp.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {
    Ride getRideById(Long rideId);
    void matchWithDriver(RideRequestDto rideRequestDto);
    Ride createNewRide(RideRequestDto rideRequestDto, Driver driver);
    Ride updateRideStatus(Long rideId,RideStatus rideStatus);
    Page<Ride> getAllRideOfDriver(Long driverId, PageRequest pageRequest);
    Page<Ride> getAllRideOfRider(Long riderId, PageRequest pageRequest);
}
