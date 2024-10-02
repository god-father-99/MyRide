package com.aditya.project.uber.uberApp.strategies.impl;

import com.aditya.project.uber.uberApp.entities.Driver;
import com.aditya.project.uber.uberApp.entities.RideRequest;
import com.aditya.project.uber.uberApp.repositories.DriverRepository;
import com.aditya.project.uber.uberApp.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
        return driverRepository.findTenNearestDriver(rideRequest.getPickupLocation());
    }
}
