package com.aditya.project.uber.uberApp.entities;


import com.aditya.project.uber.uberApp.entities.enums.PaymentMethod;
import com.aditya.project.uber.uberApp.entities.enums.PaymentStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;

    @OneToOne(fetch = FetchType.LAZY)
    Ride ride;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private Double amount;

    @CreationTimestamp
    private LocalDateTime paymentTime;
}
