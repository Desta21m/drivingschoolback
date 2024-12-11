package com.example.drivingschoolmanagement.controller;

import com.example.drivingschoolmanagement.model.VehicleAssignment;
import com.example.drivingschoolmanagement.service.VehicleAssignmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class VehicleAssignmentController {
    private final VehicleAssignmentService vehicleAssignmentService;

    public VehicleAssignmentController(VehicleAssignmentService vehicleAssignmentService) {
        this.vehicleAssignmentService = vehicleAssignmentService;
    }

    @GetMapping
    public List<VehicleAssignment> getAllAssignments() {
        return vehicleAssignmentService.getAllVehicleAssignments();
    }

    @GetMapping("/{assignmentId}")
    public VehicleAssignment getAssignmentById(@PathVariable Integer assignmentId) {
        return vehicleAssignmentService.getVehicleAssignmentById(assignmentId);
    }

    @PostMapping
    public VehicleAssignment createAssignment(@RequestBody VehicleAssignment assignment) {
        return vehicleAssignmentService.saveVehicleAssignment(assignment);
    }

    @DeleteMapping("/{assignmentId}")
    public void deleteAssignment(@PathVariable Integer assignmentId) {
        vehicleAssignmentService.deleteVehicleAssignment(assignmentId);
    }

    // Endpoints for procedures:

    @GetMapping("/active")
    public List<VehicleAssignment> getActiveAssignments() {
        return vehicleAssignmentService.getActiveAssignments();
    }

    @GetMapping("/inactive")
    public List<VehicleAssignment> getInactiveAssignments() {
        return vehicleAssignmentService.getInactiveAssignments();
    }
}


