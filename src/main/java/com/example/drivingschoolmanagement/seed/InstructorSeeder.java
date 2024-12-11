package com.example.drivingschoolmanagement.seed;

import com.github.javafaker.Faker;
import com.example.drivingschoolmanagement.model.Instructor;
import com.example.drivingschoolmanagement.service.InstructorService;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@Profile("seed")
public class InstructorSeeder {

    @Autowired
    private InstructorService instructorService;

    @PostConstruct
    public void init() {
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            Instructor instructor = new Instructor();
            instructor.setFirstName(faker.name().firstName());
            instructor.setLastName(faker.name().lastName());
            instructor.setDateOfBirth(convertToLocalDateViaInstant(faker.date().birthday()));
            instructor.setPhoneNumber(faker.phoneNumber().phoneNumber());
            instructor.setEmail(faker.internet().emailAddress());
            instructor.setAddress(faker.address().fullAddress());
            instructor.setHireDate(LocalDate.now());
            instructorService.saveInstructor(instructor);
        }
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
