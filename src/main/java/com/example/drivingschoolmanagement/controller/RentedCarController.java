package com.example.drivingschoolmanagement.controller;

import com.example.drivingschoolmanagement.DTO.RenteCarDTO;
import com.example.drivingschoolmanagement.model.RentedCar;
import com.example.drivingschoolmanagement.service.RenteCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rented-cars")
public class RentedCarController {

    private final RenteCarService renteCarService;

    @Autowired
    public RentedCarController(RenteCarService renteCarService) {
        this.renteCarService = renteCarService;
    }

    // Get all rented cars
    @GetMapping
    public List<RentedCar> getAllRentedCars() {
        return renteCarService.getAllRentedCars();
    }

    // Get rented car by ID
    @GetMapping("/{id}")
    public ResponseEntity<RentedCar> getRentedCarById(@PathVariable Integer id) {
        RentedCar rentedCar = renteCarService.gRentedCarbyId(id);
        if (rentedCar != null) {
            return ResponseEntity.ok(rentedCar);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Create a new rented car
    @PostMapping
    public ResponseEntity<RentedCar> createRentedCar(@RequestBody RenteCarDTO renteCarDTO) {
        try {
            RentedCar newRentedCar = renteCarService.saveRentedCar(renteCarDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newRentedCar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Delete rented car by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRentedCar(@PathVariable Integer id) {
        try {
            renteCarService.deleterentedCar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

