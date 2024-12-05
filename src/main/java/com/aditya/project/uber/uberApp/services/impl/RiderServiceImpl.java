package com.aditya.project.uber.uberApp.services.impl;

import com.aditya.project.uber.uberApp.dto.RideDto;
import com.aditya.project.uber.uberApp.dto.RideRequestDto;
import com.aditya.project.uber.uberApp.dto.RiderDto;
import com.aditya.project.uber.uberApp.entities.*;
import com.aditya.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.aditya.project.uber.uberApp.entities.enums.RideStatus;
import com.aditya.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.aditya.project.uber.uberApp.repositories.RideRequestRepository;
import com.aditya.project.uber.uberApp.repositories.RiderRepository;
import com.aditya.project.uber.uberApp.services.DriverService;
import com.aditya.project.uber.uberApp.services.RideService;
import com.aditya.project.uber.uberApp.services.RiderService;
import com.aditya.project.uber.uberApp.strategies.RideStrategyManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {
    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideService rideService;
    private final DriverService driverService;

     @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider=getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRider(rider);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        Double fare =rideStrategyManager.rideFareCalculationStrategy().calculateRideFare(rideRequest);
        rideRequest.setFare(fare);
        RideRequest savedRideRequest=rideRequestRepository.save(rideRequest);

        //TODO : Send the notification to drivers about the ride request
        List<Driver> driver=rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDrivers(rideRequest);
        return modelMapper.map(savedRideRequest,RideRequestDto.class);


    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Rider rider=getCurrentRider();
        Ride ride=rideService.getRideById(rideId);
        if(!(rider==ride.getRider()))
            throw new RuntimeException("Current Rider does not own this ride");
        if(!ride.getRideRequestStatus().equals(RideRequestStatus.CONFIRMED))
            throw new RuntimeException("Ride cannot be cancelled, invalid status : "+ride.getRideRequestStatus());
        Ride savedRide= rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        driverService.updateDriverAvailability(ride.getDriver());
        return modelMapper.map(savedRide,RideDto.class);
    }

    @Override
    public RiderDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return modelMapper.map(getCurrentRider(),RiderDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Rider rider=getCurrentRider();
        return rideService.getAllRideOfRider(rider, pageRequest).map(ride -> modelMapper.map(ride, RideDto.class));    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider
                .builder()
                .user(user)
                .rating(0.0)
                .build();
        return rider;
    }

    @Override
    public Rider getCurrentRider() {
        //TODO : implement spring sequrity here
        return riderRepository.findById(1L).orElseThrow(()->new ResourceNotFoundException("Rider not found with id : "+1));

    }
}
