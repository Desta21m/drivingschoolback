package com.example.drivingschoolmanagement.service;

import com.example.drivingschoolmanagement.model.VehicleAssignment;
import com.example.drivingschoolmanagement.repository.VehicleAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VehicleAssignmentService {
    private final VehicleAssignmentRepository vehicleAssignmentRepository;

    @Autowired
    public VehicleAssignmentService(VehicleAssignmentRepository vehicleAssignmentRepository) {
        this.vehicleAssignmentRepository = vehicleAssignmentRepository;
    }

    public List<VehicleAssignment> getAllVehicleAssignments() {
        return vehicleAssignmentRepository.findAll();
    }

    public VehicleAssignment getVehicleAssignmentById(Integer assignmentId) {
        return vehicleAssignmentRepository.findById(assignmentId).orElse(null);
    }

    public VehicleAssignment saveVehicleAssignment(VehicleAssignment vehicleAssignment) {
        return vehicleAssignmentRepository.save(vehicleAssignment);
    }

    public void deleteVehicleAssignment(Integer assignmentId) {
        vehicleAssignmentRepository.deleteById(assignmentId);
    }
    public List<VehicleAssignment> getActiveAssignments() {
        return vehicleAssignmentRepository.findActiveAssignments();
    }

    public List<VehicleAssignment> getInactiveAssignments() {
        return vehicleAssignmentRepository.findInactiveAssignments();
    }

}
