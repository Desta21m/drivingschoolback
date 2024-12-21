package com.example.drivingschoolmanagement.seed;

import com.github.javafaker.Faker;
import com.example.drivingschoolmanagement.model.Student;
import com.example.drivingschoolmanagement.service.StudentService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@Profile("seed")
public class StudentSeeder {

    @Autowired
    private StudentService studentService;

    @PostConstruct
    public void init() {
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            // student.setFirstName(faker.name().firstName());
            // student.setLastName(faker.name().lastName());
            // student.setDateOfBirth(convertToLocalDateViaInstant(faker.date().birthday()));
            // student.setPhoneNumber(faker.phoneNumber().phoneNumber());
            // student.setEmail(faker.internet().emailAddress());
            // student.setAddress(faker.address().fullAddress());
            student.setStartDate(LocalDate.now());
            studentService.saveStudent(student);
        }
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}

