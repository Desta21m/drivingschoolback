package com.example.drivingschoolmanagement.repository;

import com.example.drivingschoolmanagement.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    // @Procedure(name = "get_vehicles_needing_maintenance")
    // List<Vehicle> findVehiclesNeedingMaintenance();
    // @Procedure(name = "get_assigned_vehicles")
    // List<Vehicle> findAssignedVehicles();
    List<Vehicle> findByCanBeRentedTrueAndIsReantedFalse();

        // Method to get vehicles needing maintenance (maintenance date is today or earlier)
    @Query("SELECT v FROM Vehicle v WHERE v.maintenanceDate <= :today")
    List<Vehicle> findVehiclesNeedingMaintenance(LocalDate today);

    // Method to get assigned vehicles (vehicles with an active assignment)
    @Query("SELECT DISTINCT v FROM Vehicle v JOIN v.assignments va WHERE va.status = true")
    List<Vehicle> findAssignedVehicles();

    // Method to get rented vehicles (vehicles that are currently rented)
    @Query("SELECT v FROM Vehicle v WHERE v.isReanted = true")
    List<Vehicle> findRentedVehicles();

        @Query("SELECT v FROM Vehicle v " +
           "WHERE v.isReanted = false " +
           "AND v.maintenanceDate != :today " +
           "AND NOT EXISTS (SELECT 1 FROM VehicleAssignment va WHERE va.vehicle = v AND va.status = true) " +
           "AND v.canBeRented = true")
    List<Vehicle> findAssignableVehicles(@Param("today") LocalDate today);
}
