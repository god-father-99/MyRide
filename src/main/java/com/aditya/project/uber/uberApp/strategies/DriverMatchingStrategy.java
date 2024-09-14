package com.aditya.project.uber.uberApp.strategies;


import com.aditya.project.uber.uberApp.entities.Driver;
import com.aditya.project.uber.uberApp.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {
    public List<Driver> findMatchingDrivers(RideRequest rideRequest);
}
