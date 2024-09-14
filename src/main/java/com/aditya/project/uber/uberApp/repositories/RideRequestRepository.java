package com.aditya.project.uber.uberApp.repositories;

import com.aditya.project.uber.uberApp.entities.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {
}
