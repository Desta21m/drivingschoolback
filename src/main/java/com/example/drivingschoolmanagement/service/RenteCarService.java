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
        Customer cin=newR.getCustomer();
        Vehicle v= vehicleRepository.findById(newR.getVihecleId()).orElse(null);
        if(cin==null){
            Student s= studentRepository.findById(newR.getStudId()).orElse(null);
            Instructor i=instructorRepository.findById(newR.getLectId()).orElse(null);
            if(s==null){
                i.getRentalHistory().add(rentedCar);
                rentedCar.setRenter( i);
            }
            else {
                s.getRentalHistory().add(rentedCar);
                rentedCar.setRenter(s);
            }
            
        }
        else{
            Customer c= customerRepository.findByPhoneNumber(cin.getPhoneNumber()).orElse(null);
            if(c==null){
                Customer cc= new Customer();
                cc.setPhoneNumber(cin.getPhoneNumber());
                cc.setAddress(cin.getAddress());
                cc.setCompanyName(cin.getCompanyName());
                cc.setDateOfBirth(cin.getDateOfBirth());
                cc.setEmail(cin.getEmail());
                cc.setFirstName(cin.getFirstName());
                cc.setLastName(cin.getLastName());
                cc.setPhoneNumber(cin.getPhoneNumber());
                cc.setReferralSource(cin.getReferralSource());
                cc.getRentalHistory().add(rentedCar);

                rentedCar.setRenter(customerRepository.save(cc));
            }
            else{
                c.getRentalHistory().add(rentedCar);
                rentedCar.setRenter(c);
            }
        }
        rentedCar.setVehicle(v);
        rentedCar.setIsActive(true);
        rentedCar.setRentalEndDate(newR.getRentalEndDate());
        rentedCar.setRentalStartDate(newR.getRentalStartDate());
        rentedCar.setRentalPrice(calculateRentalPrice(newR.getRentalStartDate(),newR.getRentalEndDate(),v.getRantPerHour()));


        

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
