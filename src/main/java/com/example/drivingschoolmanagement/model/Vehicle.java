package com.example.drivingschoolmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "vehicles")
@NamedStoredProcedureQuery(
        name = "get_vehicles_needing_maintenance",
        procedureName = "get_vehicles_needing_maintenance",
        resultClasses = {Vehicle.class},
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
        }
)
@NamedStoredProcedureQuery(
        name = "get_assigned_vehicles",
        procedureName = "get_assigned_vehicles",
        resultClasses = {Vehicle.class},
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
        }
)
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vehicleId;

    private String make;

    private String model;

    private String year;

    private String licensePlate;

    private String color;

    private double rantPerHour;

    private LocalDate registrationDate;

    private LocalDate maintenanceDate;

    @OneToMany(mappedBy = "vehicle")
    @JsonIgnore
    private List<VehicleAssignment> assignments;

    private Boolean canBeRented; // Indicates if the vehicle can be rented
    private Boolean isReanted;

    @OneToMany(mappedBy = "vehicle")
    @JsonIgnore
    private List<RentedCar> rentedCars;
}
