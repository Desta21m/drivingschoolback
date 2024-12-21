package com.example.drivingschoolmanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "rented_cars")
public class RentedCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rentalId;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User renter; // Can be an Instructor, Student, or Customer

    private LocalDateTime rentalStartDate;

    private LocalDateTime rentalEndDate;

    private Double rentalPrice;

    private Boolean isActive; // Indicates if the rental is ongoing
}

