package com.example.drivingschoolmanagement.seed;

import com.example.drivingschoolmanagement.model.Course;
import com.example.drivingschoolmanagement.repository.CourseRepository;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("seed")
public class CourseSeeder {
    @Autowired
    private CourseRepository courseRepository;

    @PostConstruct
    public void init() {
        Faker faker = new Faker();
        String[] categories = {"A", "A1", "A2", "AM", "B", "B1", "C", "C1", "D", "D1", "T", "P"};
        for (int i = 0; i < 10; i++) {
            Course course = new Course();
            String category = faker.options().option(categories);
            course.setCourseName(category);
            course.setCourseDescription("This is a course for category: " + category);
            course.setCourseDuration(faker.number().numberBetween(1, 12));
            course.setCourseFee(new BigDecimal(faker.number().randomDouble(2, 500, 2000)));
            courseRepository.save(course);
        }
    }
}
