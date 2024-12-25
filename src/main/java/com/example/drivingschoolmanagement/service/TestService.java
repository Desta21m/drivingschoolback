package com.example.drivingschoolmanagement.service;

import com.example.drivingschoolmanagement.model.Course;
import com.example.drivingschoolmanagement.model.Instructor;
import com.example.drivingschoolmanagement.model.Student;
import com.example.drivingschoolmanagement.model.Test;
import com.example.drivingschoolmanagement.repository.CourseRepository;
import com.example.drivingschoolmanagement.repository.InstructorRepository;
import com.example.drivingschoolmanagement.repository.StudentRepository;
import com.example.drivingschoolmanagement.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestService {
    private final TestRepository testRepository;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;
    @Autowired
    public TestService(TestRepository testRepository,CourseRepository courseRepository,InstructorRepository instructorRepository,StudentRepository studentRepository) {
        this.testRepository = testRepository;
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
        this.studentRepository = studentRepository;
    }

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Test getTestById(Integer testId) {
        return testRepository.findById(testId).orElse(null);
    }

    public Test saveTest(Test test) {
        Course c= test.getCourse();
        Instructor in= test.getInstructor();
        Student s= test.getStudent();

        s.getTests().add(test);
        in.getTests().add(test);
        c.getTests().add(test);

        studentRepository.save(s);
        instructorRepository.save(in);
        courseRepository.save(c);
        return testRepository.save(test);
    }

    public void deleteTest(Integer testId) {
        testRepository.deleteById(testId);
    }

    public List<Test> getPassedTestsByStudent(Integer studentId) {
        return testRepository.findPassedTestsByStudent(studentId);
    }

    public Double getAverageTestScoreByCourse(Integer courseId) {
        return testRepository.findAverageTestScoreByCourse(courseId);
    }
}
