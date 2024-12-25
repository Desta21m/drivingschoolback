package com.example.drivingschoolmanagement.service;

import com.example.drivingschoolmanagement.model.Vehicle;
import com.example.drivingschoolmanagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
    public List<Vehicle> rantRadyVehicles() {
        return vehicleRepository.findByCanBeRentedTrueAndIsReantedFalse();
    }

    public Vehicle getVehicleById(Integer vehicleId) {
        return vehicleRepository.findById(vehicleId).orElse(null);
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        vehicle.setIsReanted(false);
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Integer vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }
     // Method to get vehicles needing maintenance
    public List<Vehicle> getVehiclesNeedingMaintenance() {
        LocalDate today = LocalDate.now(); // Get today's date
        return vehicleRepository.findVehiclesNeedingMaintenance(today);
    }

    // Method to get assigned vehicles
    public List<Vehicle> getAssignedVehicles() {
        return vehicleRepository.findAssignedVehicles();
    }

    // Method to get rented vehicles
    public List<Vehicle> getRentedVehicles() {
        return vehicleRepository.findRentedVehicles();
    }

    public List<Vehicle> assignableVehicles() {
        LocalDate today = LocalDate.now();
        return vehicleRepository.findAssignableVehicles(today);
    }

}
