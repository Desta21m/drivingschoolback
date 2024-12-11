package com.example.drivingschoolmanagement.service;

import com.example.drivingschoolmanagement.model.Vehicle;
import com.example.drivingschoolmanagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Vehicle getVehicleById(Integer vehicleId) {
        return vehicleRepository.findById(vehicleId).orElse(null);
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Integer vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }
    public List<Vehicle> getVehiclesNeedingMaintenance() {
        return vehicleRepository.findVehiclesNeedingMaintenance();
    }
    public List<Vehicle> getAssignedVehicles() {
        return vehicleRepository.findAssignedVehicles();
    }

}
