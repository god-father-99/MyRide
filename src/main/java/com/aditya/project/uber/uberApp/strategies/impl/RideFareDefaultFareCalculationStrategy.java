package com.aditya.project.uber.uberApp.strategies.impl;

import com.aditya.project.uber.uberApp.dto.RideRequestDto;
import com.aditya.project.uber.uberApp.entities.RideRequest;
import com.aditya.project.uber.uberApp.services.DistanceService;
import com.aditya.project.uber.uberApp.services.impl.DistanceServiceOSRMImpl;
import com.aditya.project.uber.uberApp.strategies.DistanceApiManager;
import com.aditya.project.uber.uberApp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceApiManager distanceApiManager;
    @Override
    public Double calculateRideFare(RideRequest rideRequest) {
        Double distance= distanceApiManager.distanceService().calculateDistance(rideRequest.getPickupLocation(),rideRequest.getDropOffLocation());
        return distance*RIDE_FARE_MULTIPLIER;
    }
}
