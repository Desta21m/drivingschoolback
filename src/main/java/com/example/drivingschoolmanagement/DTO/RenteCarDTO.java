package com.example.drivingschoolmanagement.DTO;

import java.time.LocalDateTime;

import com.example.drivingschoolmanagement.model.Customer;

import lombok.Data;

@Data
public class RenteCarDTO {
    private Customer customer;
    private Integer studId;
    private Integer LectId;
    private Integer vihecleId;

    private LocalDateTime rentalStartDate;

    private LocalDateTime rentalEndDate;

}
