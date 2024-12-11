package com.example.drivingschoolmanagement.controller;

import com.example.drivingschoolmanagement.model.Instructor;
import com.example.drivingschoolmanagement.service.InstructorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public List<Instructor> getAllInstructors() {
        return instructorService.getAllInstructors();
    }

    @GetMapping("/{instructorId}")
    public Instructor getInstructorById(@PathVariable Integer instructorId) {
        return instructorService.getInstructorById(instructorId);
    }

    @PostMapping
    public Instructor createInstructor(@RequestBody Instructor instructor) {
        return instructorService.saveInstructor(instructor);
    }

    @DeleteMapping("/{instructorId}")
    public void deleteInstructor(@PathVariable Integer instructorId) {
        instructorService.deleteInstructor(instructorId);
    }

    // Endpoints for procedures:


    @GetMapping("/max_lessons_next_week")
    public List<Instructor> getInstructorsWithMaxLessonsNextWeek() {
        return instructorService.getInstructorsWithMaxLessonsNextWeek();
    }

    @GetMapping("/birthday/{month}")
    public List<Instructor> getInstructorsWithBirthdayInMonth(@PathVariable Integer month) {
        return instructorService.getInstructorsWithBirthdayInMonth(month);
    }
}


