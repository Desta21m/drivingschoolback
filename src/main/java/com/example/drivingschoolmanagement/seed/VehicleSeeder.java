package com.example.drivingschoolmanagement.seed;

import com.github.javafaker.Faker;
import com.example.drivingschoolmanagement.model.Vehicle;
import com.example.drivingschoolmanagement.service.VehicleService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Component
@Profile("seed")
public class VehicleSeeder {

    @Autowired
    private VehicleService vehicleService;

    @PostConstruct
    public void init() {
        Faker faker = new Faker();
        List<String> colors = Arrays.asList("Black", "White", "Red", "Blue", "Yellow", "Green", "Gray");
        for (int i = 0; i < 10; i++) {
            Vehicle vehicle = new Vehicle();
            vehicle.setMake(faker.company().name());
            vehicle.setModel(faker.bothify("Model ?##"));
            vehicle.setYear(String.valueOf(faker.number().numberBetween(1990, 2023)));
            vehicle.setLicensePlate(faker.bothify("?#??###"));
            vehicle.setColor(colors.get(faker.random().nextInt(colors.size())));
            vehicle.setRegistrationDate(convertToLocalDateViaInstant(faker.date().past(3000, TimeUnit.DAYS)));
            vehicle.setMaintenanceDate(convertToLocalDateViaInstant(faker.date().past(365, TimeUnit.DAYS)));
            vehicleService.saveVehicle(vehicle);
        }
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}

