package com.example.drivingschoolmanagement.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.drivingschoolmanagement.DTO.RenteCarDTO;
import com.example.drivingschoolmanagement.model.Customer;
import com.example.drivingschoolmanagement.model.Instructor;
import com.example.drivingschoolmanagement.model.RentedCar;
import com.example.drivingschoolmanagement.model.Student;
import com.example.drivingschoolmanagement.model.Vehicle;
import com.example.drivingschoolmanagement.repository.CustomerRepository;
import com.example.drivingschoolmanagement.repository.InstructorRepository;
import com.example.drivingschoolmanagement.repository.RentedCarRepository;
import com.example.drivingschoolmanagement.repository.StudentRepository;
import com.example.drivingschoolmanagement.repository.VehicleRepository;

@Service
@Transactional
public class RenteCarService {
     private final  RentedCarRepository rentedCarRepository;
     private final  VehicleRepository vehicleRepository;
     private final StudentRepository   studentRepository;
     private final InstructorRepository instructorRepository;
     private final CustomerRepository customerRepository;

     @Autowired
    public RenteCarService(RentedCarRepository rentedCarRepository,VehicleRepository vehicleRepository,StudentRepository   studentRepository,InstructorRepository instructorRepository,CustomerRepository customerRepository) {
        this.rentedCarRepository = rentedCarRepository;
        this.vehicleRepository = vehicleRepository;
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
        this.customerRepository = customerRepository;
    }

    public List<RentedCar> getAllRentedCars() {
        return rentedCarRepository.findAll();
    }

    public RentedCar gRentedCarbyId(Integer ID) {
        return rentedCarRepository.findById(ID).orElse(null);
    }

    public RentedCar saveRentedCar(RenteCarDTO newR) {
        RentedCar rentedCar = new RentedCar();
        Vehicle v = vehicleRepository.findById(newR.getVihecleId())
            .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with ID: " + newR.getVihecleId()));
        
        if (newR.getCustomer() != null) {
            Customer c = customerRepository.findById(newR.getCustomer())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + newR.getCustomer()));
            c.getRentalHistory().add(rentedCar);
            rentedCar.setRenter(c);
        } else if (newR.getStudId() != null) {
            Student s = studentRepository.findById(newR.getStudId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + newR.getStudId()));
            s.getRentalHistory().add(rentedCar);
            rentedCar.setRenter(s);
        } else if (newR.getLectId() != null) {
            Instructor i = instructorRepository.findById(newR.getLectId())
                .orElseThrow(() -> new IllegalArgumentException("Instructor not found with ID: " + newR.getLectId()));
            i.getRentalHistory().add(rentedCar);
            rentedCar.setRenter(i);
        } else {
            throw new IllegalArgumentException("No valid renter found (customer, student, or instructor).");
        }
    
        rentedCar.setVehicle(v);
        rentedCar.setIsActive(true);
        rentedCar.setRentalStartDate(newR.getRentalStartDate());
        rentedCar.setRentalEndDate(newR.getRentalEndDate());
        rentedCar.setRentalPrice(calculateRentalPrice(
            newR.getRentalStartDate(), newR.getRentalEndDate(), v.getRantPerHour()
        ));
    
        v.setIsReanted(true);
        vehicleRepository.save(v);
    
        return rentedCarRepository.save(rentedCar);
    }
    
    
    public void deleterentedCar(Integer rentedCarId) {
        rentedCarRepository.deleteById(rentedCarId);
    }

    private double calculateRentalPrice(LocalDateTime rentalStartDate,LocalDateTime rentalEndDate, double rentalPerHour) {
        // Ensure rentalEndDate is after rentalStartDate
        if (rentalStartDate == null || rentalEndDate == null || rentalEndDate.isBefore(rentalStartDate)) {
            throw new IllegalArgumentException("Rental end date must be after rental start date");
        }

        // Calculate the duration between start and end dates in hours
        long durationInHours = Duration.between(rentalStartDate, rentalEndDate).toHours();

        // Calculate and return the total rental price
        return durationInHours * rentalPerHour;
    }
    
}
