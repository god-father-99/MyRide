package com.aditya.project.uber.uberApp.services.impl;


import com.aditya.project.uber.uberApp.entities.RideRequest;
import com.aditya.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.aditya.project.uber.uberApp.repositories.RideRepository;
import com.aditya.project.uber.uberApp.repositories.RideRequestRepository;
import com.aditya.project.uber.uberApp.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {
    private final RideRequestRepository repository;
    private final RideRepository rideRepository;
    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return repository.findById(rideRequestId).orElseThrow(()->new ResourceNotFoundException("RideRequest with id " + rideRequestId + " not found"));
    }

    @Override
    public void update(RideRequest rideRequest) {
        RideRequest toSave=rideRequestRepository.findById(rideRequest.getId()).orElseThrow(()->new ResourceNotFoundException("rideRequest not found with id : "+rideRequest.getId()));
        rideRequestRepository.save(rideRequest);
    }
}
