package com.example.drivingschoolmanagement.controller;

import com.example.drivingschoolmanagement.model.Course;
import com.example.drivingschoolmanagement.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{courseId}")
    public Course getCourseById(@PathVariable Integer courseId) {
        return courseService.getCourseById(courseId);
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable Integer courseId) {
        courseService.deleteCourse(courseId);
    }

    // Endpoints for procedures:

    @GetMapping("/today_lessons")
    public List<Course> getCoursesWithLessonsToday() {
        return courseService.getCoursesWithLessonsToday();
    }

    @GetMapping("/max_lessons")
    public List<Course> getCoursesWithMaxLessons() {
        return courseService.getCoursesWithMaxLessons();
    }
}
