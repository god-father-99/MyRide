package com.aditya.project.uber.uberApp.strategies.impl;

import com.aditya.project.uber.uberApp.dto.RideRequestDto;
import com.aditya.project.uber.uberApp.entities.RideRequest;
import com.aditya.project.uber.uberApp.services.DistanceService;
import com.aditya.project.uber.uberApp.services.impl.DistanceServiceOSRMImpl;
import com.aditya.project.uber.uberApp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;
    @Override
    public Double calculateRideFare(RideRequest rideRequest) {
        Double distance=distanceService.calculateDistance(rideRequest.getPickupLocation(),rideRequest.getDropOfLocation());
        return distance*RIDE_FARE_MULTIPLIER;
    }
}
