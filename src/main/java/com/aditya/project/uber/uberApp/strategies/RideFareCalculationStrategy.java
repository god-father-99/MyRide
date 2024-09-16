package com.aditya.project.uber.uberApp.strategies;

import com.aditya.project.uber.uberApp.entities.RideRequest;


public interface RideFareCalculationStrategy {

    double RIDE_FARE_MULTIPLIER = 10;

    Double calculateRideFare(RideRequest rideRequest);
}
