package com.aditya.project.uber.uberApp.repositories;

import com.aditya.project.uber.uberApp.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RiderRepository  extends JpaRepository<Rider, Long> {
}
