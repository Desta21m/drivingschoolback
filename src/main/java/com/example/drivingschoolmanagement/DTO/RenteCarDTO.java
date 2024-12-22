package com.example.drivingschoolmanagement.DTO;

import java.time.LocalDateTime;

import com.example.drivingschoolmanagement.model.Customer;

import lombok.Data;

@Data
public class RenteCarDTO {
    private Integer customer;
    private Integer studId;
    private Integer lectId;
    private Integer vihecleId;

    private LocalDateTime rentalStartDate;

    private LocalDateTime rentalEndDate;

}
