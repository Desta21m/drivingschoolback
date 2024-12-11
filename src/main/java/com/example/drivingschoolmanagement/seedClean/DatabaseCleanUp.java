package com.example.drivingschoolmanagement.seedClean;

import com.example.drivingschoolmanagement.repository.CourseRepository;
import com.example.drivingschoolmanagement.repository.InstructorRepository;
import com.example.drivingschoolmanagement.repository.StudentRepository;
import com.example.drivingschoolmanagement.repository.VehicleRepository;
import com.example.drivingschoolmanagement.repository.VehicleAssignmentRepository;
import com.example.drivingschoolmanagement.repository.LessonRepository;
import com.example.drivingschoolmanagement.repository.TestRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;



@Component
@Profile("dev")
public class DatabaseCleanUp {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleAssignmentRepository vehicleAssignmentRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private TestRepository testRepository;

    @PostConstruct
    public void deleteAll() {
        testRepository.deleteAll();
        lessonRepository.deleteAll();
        vehicleAssignmentRepository.deleteAll();
        vehicleRepository.deleteAll();
        studentRepository.deleteAll();
        instructorRepository.deleteAll();
        courseRepository.deleteAll();
    }
}
