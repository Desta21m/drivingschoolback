package com.example.drivingschoolmanagement.service;

import com.example.drivingschoolmanagement.model.Test;
import com.example.drivingschoolmanagement.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestService {
    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Test getTestById(Integer testId) {
        return testRepository.findById(testId).orElse(null);
    }

    public Test saveTest(Test test) {
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
