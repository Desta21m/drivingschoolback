package com.example.drivingschoolmanagement.seed;

import com.github.javafaker.Faker;
import com.example.drivingschoolmanagement.model.Vehicle;
import com.example.drivingschoolmanagement.model.VehicleAssignment;
import com.example.drivingschoolmanagement.service.VehicleService;
import com.example.drivingschoolmanagement.service.VehicleAssignmentService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("seed")
public class VehicleAssignmentSeeder {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleAssignmentService vehicleAssignmentService;

    @PostConstruct
    public void init() {
        Faker faker = new Faker();
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        for (Vehicle vehicle : vehicles) {
            VehicleAssignment vehicleAssignment = new VehicleAssignment();
            vehicleAssignment.setVehicle(vehicle);
            vehicleAssignment.setStatus(faker.bool().bool());
            vehicleAssignmentService.saveVehicleAssignment(vehicleAssignment);
        }
    }
}
