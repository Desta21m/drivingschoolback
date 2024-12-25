package com.example.drivingschoolmanagement.controller;

import com.example.drivingschoolmanagement.model.Lesson;
import com.example.drivingschoolmanagement.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {
    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    public List<Lesson> getAllLessons() {
        return lessonService.getAllLessons();
    }

    @GetMapping("/{lessonId}")
    public Lesson getLessonById(@PathVariable Integer lessonId) {
        return lessonService.getLessonById(lessonId);
    }

    @PostMapping
    public Lesson createLesson(@RequestBody Lesson lesson) {
        return lessonService.saveLesson(lesson);
    }

    @DeleteMapping("/{lessonId}")
    public void deleteLesson(@PathVariable Integer lessonId) {
        lessonService.deleteLesson(lessonId);
    }

    // Endpoints for procedures:

    @GetMapping("/today")
    public List<Lesson> getLessonsToday() {
        return lessonService.getLessonsForToday();
    }

    @GetMapping("/upcoming/{instructorId}")
    public List<Lesson> getUpcomingLessonsByInstructor(@PathVariable Integer instructorId) {
        return lessonService.getUpcomingLessonsByInstructor(instructorId);
    }
}

