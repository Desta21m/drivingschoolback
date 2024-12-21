package com.example.drivingschoolmanagement.repository;

import com.example.drivingschoolmanagement.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    @Procedure(name = "get_vehicles_needing_maintenance")
    List<Vehicle> findVehiclesNeedingMaintenance();
    @Procedure(name = "get_assigned_vehicles")
    List<Vehicle> findAssignedVehicles();
    List<Vehicle> findByCanBeRentedTrueAndIsReantedFalse();
}
