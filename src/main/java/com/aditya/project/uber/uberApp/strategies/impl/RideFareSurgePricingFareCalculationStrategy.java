package com.aditya.project.uber.uberApp.strategies.impl;

import com.aditya.project.uber.uberApp.entities.RideRequest;
import com.aditya.project.uber.uberApp.services.DistanceService;
import com.aditya.project.uber.uberApp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {
    private final DistanceService distanceService;
    private static final double SURGE_FACTOR=2;
    @Override
    public Double calculateRideFare(RideRequest rideRequest) {
        Double distance=distanceService.calculateDistance(rideRequest.getPickupLocation(),rideRequest.getDropOfLocation());
        return distance*RIDE_FARE_MULTIPLIER*SURGE_FACTOR;
    }
}
