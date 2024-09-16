package com.aditya.project.uber.uberApp.strategies;


import com.aditya.project.uber.uberApp.services.DistanceService;
import com.aditya.project.uber.uberApp.services.impl.DistanceServiceOSRMImpl;
import com.aditya.project.uber.uberApp.services.impl.DistanceServiceOlaImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DistanceApiManager {
    private final DistanceServiceOSRMImpl distanceServiceOSRM;
    private final DistanceServiceOlaImpl distanceServiceOla;

    public DistanceService distanceService() {
        return distanceServiceOla;
    }
}
