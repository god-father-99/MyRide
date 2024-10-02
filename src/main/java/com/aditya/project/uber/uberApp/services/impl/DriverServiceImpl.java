package com.aditya.project.uber.uberApp.services.impl;

import com.aditya.project.uber.uberApp.dto.DriverDto;
import com.aditya.project.uber.uberApp.dto.RideDto;
import com.aditya.project.uber.uberApp.dto.RiderDto;
import com.aditya.project.uber.uberApp.entities.Driver;
import com.aditya.project.uber.uberApp.entities.Ride;
import com.aditya.project.uber.uberApp.entities.RideRequest;
import com.aditya.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.aditya.project.uber.uberApp.entities.enums.RideStatus;
import com.aditya.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.aditya.project.uber.uberApp.repositories.DriverRepository;
import com.aditya.project.uber.uberApp.services.DriverService;
import com.aditya.project.uber.uberApp.services.RideRequestService;
import com.aditya.project.uber.uberApp.services.RideService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING))
            throw new RuntimeException("RideRequest is already accepted by a driver : "+rideRequest.getRideRequestStatus());

        Driver currentDriver=getCurrentDriver();
        if(!currentDriver.isAvailable())
            throw new RuntimeException("Driver is not available");

        currentDriver.setAvailable(false);
        Driver savedDriver=driverRepository.save(currentDriver);
        Ride ride=rideService.createNewRide(rideRequest, savedDriver);
        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto startRide(Long rideId,String otp) {
        Ride ride=rideService.getRideById(rideId);
        Driver currentDriver=getCurrentDriver();
        if(!currentDriver.equals(ride.getDriver()))
            throw new RuntimeException("Driver is not accepted because someone else had accepted it : ");
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED))
            throw new RuntimeException("RideRequest is not accepted because it is not CONFIRMED, status is : "+ride.getRideStatus());
        if(!otp.equals(ride.getOtp()))
            throw new RuntimeException("incorrect OTP");
        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide=rideService.updateRideStatus(ride,RideStatus.ONGOING);
        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(3L).orElseThrow(()->new ResourceNotFoundException("Driver not found with id : "+3));
    }
}
