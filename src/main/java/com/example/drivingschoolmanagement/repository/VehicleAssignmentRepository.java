package com.example.drivingschoolmanagement.repository;

import com.example.drivingschoolmanagement.model.VehicleAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface VehicleAssignmentRepository extends JpaRepository<VehicleAssignment, Integer> {
    @Procedure(name = "active_assignments")
    List<VehicleAssignment> findActiveAssignments();

    @Procedure(name = "inactive_assignments")
    List<VehicleAssignment> findInactiveAssignments();
}
