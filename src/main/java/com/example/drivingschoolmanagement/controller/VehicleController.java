package com.example.drivingschoolmanagement.controller;

import com.example.drivingschoolmanagement.model.Vehicle;
import com.example.drivingschoolmanagement.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{vehicleId}")
    public Vehicle getVehicleById(@PathVariable Integer vehicleId) {
        return vehicleService.getVehicleById(vehicleId);
    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.saveVehicle(vehicle);
    }


    @DeleteMapping("/{vehicleId}")
    public void deleteVehicle(@PathVariable Integer vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
    }

    // Endpoints for procedures:

    @GetMapping("/maintenance")
    public List<Vehicle> getVehiclesNeedingMaintenance() {
        return vehicleService.getVehiclesNeedingMaintenance();
    }

    @GetMapping("/assigned")
    public List<Vehicle> getAssignedVehicles() {
        return vehicleService.getAssignedVehicles();
    }
}

