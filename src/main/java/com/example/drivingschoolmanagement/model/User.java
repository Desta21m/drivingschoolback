package com.example.drivingschoolmanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)  // Use JOINED for separate tables
@Data
@Table(name = "users")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String phoneNumber;

    private String email;

    private String address;

    @OneToMany(mappedBy = "renter")
    @JsonIgnore
    private List<RentedCar> rentalHistory; // List of all rentals by the customer
}
