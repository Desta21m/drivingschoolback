package com.example.drivingschoolmanagement.controller;

import com.example.drivingschoolmanagement.model.Test;
import com.example.drivingschoolmanagement.service.TestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public List<Test> getAllTests() {
        return testService.getAllTests();
    }

    @GetMapping("/{testId}")
    public Test getTestById(@PathVariable Integer testId) {
        return testService.getTestById(testId);
    }

    @PostMapping
    public Test createTest(@RequestBody Test test) {
        return testService.saveTest(test);
    }

    @DeleteMapping("/{testId}")
    public void deleteTest(@PathVariable Integer testId) {
        testService.deleteTest(testId);
    }

    // Endpoints for procedures:

    @GetMapping("/passed/{studentId}")
    public List<Test> getPassedTestsByStudent(@PathVariable Integer studentId) {
        return testService.getPassedTestsByStudent(studentId);
    }

    @GetMapping("/average/{courseId}")
    public Double getAverageTestScoreByCourse(@PathVariable Integer courseId) {
        return testService.getAverageTestScoreByCourse(courseId);
    }
}

