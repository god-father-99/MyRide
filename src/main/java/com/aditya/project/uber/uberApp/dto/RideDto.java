package com.aditya.project.uber.uberApp.dto;

import com.aditya.project.uber.uberApp.entities.enums.PaymentMethod;
import com.aditya.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.aditya.project.uber.uberApp.entities.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideDto {
    private Long id;
    private Point pickupLocation;
    private Point destinationLocation;
    private LocalDateTime createdTime;
    private RiderDto rider;
    private DriverDto driver;
    private PaymentMethod paymentMethod;
    private RideRequestStatus rideRequestStatus;
    private RideStatus rideStatus;
    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String otp;
}
