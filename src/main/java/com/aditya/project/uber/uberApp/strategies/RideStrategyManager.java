package com.aditya.project.uber.uberApp.strategies;


import com.aditya.project.uber.uberApp.strategies.impl.DriverMatchingHighestRatedDriverStrategy;
import com.aditya.project.uber.uberApp.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.aditya.project.uber.uberApp.strategies.impl.RideFareDefaultFareCalculationStrategy;
import com.aditya.project.uber.uberApp.strategies.impl.RideFareSurgePricingFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {

    private final DriverMatchingHighestRatedDriverStrategy driverMatchingHighestRatedStrategy;
    private final DriverMatchingNearestDriverStrategy driverMatchingNearestStrategy;
    private final RideFareDefaultFareCalculationStrategy rideFareDefaultFareCalculationStrategy;
    private final RideFareSurgePricingFareCalculationStrategy rideFareSurgePricingFareCalculationStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(double riderRating){
        if(riderRating > 4.8){
            return driverMatchingHighestRatedStrategy;
        }
        else
            return driverMatchingNearestStrategy;
    }
    public RideFareCalculationStrategy rideFareCalculationStrategy(){
        //peak hour(6pm to 9pm)
        LocalTime surgeStartTime = LocalTime.of(18,0);
        LocalTime surgeEndTime = LocalTime.of(21,0);
        LocalTime currentTime = LocalTime.now();
        boolean isSurgeTime=currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);
        if(isSurgeTime)
            return rideFareSurgePricingFareCalculationStrategy;
        else
            return rideFareDefaultFareCalculationStrategy;

    }
}
