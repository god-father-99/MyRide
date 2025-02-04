package com.aditya.project.uber.uberApp.repositories;

import com.aditya.project.uber.uberApp.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository  extends JpaRepository<Wallet, Long> {
}
