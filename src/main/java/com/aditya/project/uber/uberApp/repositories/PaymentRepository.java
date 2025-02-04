package com.aditya.project.uber.uberApp.repositories;

import com.aditya.project.uber.uberApp.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
