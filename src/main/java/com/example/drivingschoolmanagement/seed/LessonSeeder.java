package com.example.drivingschoolmanagement.seed;

import com.github.javafaker.Faker;
import com.example.drivingschoolmanagement.model.*;
import com.example.drivingschoolmanagement.service.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Profile("seed")
public class LessonSeeder {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private VehicleAssignmentService vehicleAssignmentService;

    @Autowired
    private LessonService lessonService;

    @PostConstruct
    public void init() {
        Faker faker = new Faker();
        List<Course> courses = courseService.getAllCourses();
        List<Student> students = studentService.getAllStudents();
        List<Instructor> instructors = instructorService.getAllInstructors();
        List<VehicleAssignment> vehicleAssignments = vehicleAssignmentService.getAllVehicleAssignments();
        for (int i = 0; i < students.size(); i++) {
            Lesson lesson = new Lesson();
            lesson.setCourse(courses.get(i % courses.size()));
            lesson.setStudent(students.get(i));
            lesson.setInstructor(instructors.get(i % instructors.size()));
            lesson.setVehicleAssignment(vehicleAssignments.get(i % vehicleAssignments.size()));
            lesson.setStartTime(LocalDateTime.ofInstant(faker.date().past(10, 0, TimeUnit.DAYS).toInstant(), ZoneId.systemDefault()));
            lesson.setEndTime(LocalDateTime.ofInstant(faker.date().future(10, 0, TimeUnit.DAYS).toInstant(), ZoneId.systemDefault()));
            lessonService.saveLesson(lesson);
        }
    }
}

