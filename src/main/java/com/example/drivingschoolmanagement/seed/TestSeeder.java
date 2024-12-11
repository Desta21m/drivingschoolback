package com.example.drivingschoolmanagement.seed;

import com.github.javafaker.Faker;
import com.example.drivingschoolmanagement.model.*;
import com.example.drivingschoolmanagement.service.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Profile("seed")
public class TestSeeder {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private TestService testService;

    @PostConstruct
    public void init() {
        Faker faker = new Faker();
        List<Course> courses = courseService.getAllCourses();
        List<Student> students = studentService.getAllStudents();
        List<Instructor> instructors = instructorService.getAllInstructors();
        for (int i = 0; i < students.size(); i++) {
            Test test = new Test();
            test.setCourse(courses.get(i % courses.size()));
            test.setStudent(students.get(i));
            test.setInstructor(instructors.get(i % instructors.size()));
            test.setTestDate(LocalDate.now());
            int score = ThreadLocalRandom.current().nextInt(50, 101);
            test.setScore(score);
            test.setResult(score >= 70);  // let's assume that pass score is 70 and above
            testService.saveTest(test);
        }
    }
}
