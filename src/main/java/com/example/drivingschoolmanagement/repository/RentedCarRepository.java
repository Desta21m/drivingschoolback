package com.example.drivingschoolmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.drivingschoolmanagement.model.RentedCar;

public interface RentedCarRepository extends JpaRepository<RentedCar, Integer> {
    
}
