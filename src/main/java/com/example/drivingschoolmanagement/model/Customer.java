package com.example.drivingschoolmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "Customer")
public class Customer extends User {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Integer CustomerId;

    private String referralSource; // How the customer found out about the service
    private String companyName; // For business customers

}

