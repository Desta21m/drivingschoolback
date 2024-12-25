package com.example.drivingschoolmanagement.service;

import com.example.drivingschoolmanagement.model.Course;
import com.example.drivingschoolmanagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Integer courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Integer courseId) {
        courseRepository.deleteById(courseId);
    }
    // Method to get courses with lessons scheduled for today
    public List<Course> getCoursesWithLessonsToday() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(23, 59, 59); // End of day
        
        return courseRepository.findCoursesWithLessonsToday(startOfDay, endOfDay);
    }

    // Method to get courses with the maximum number of lessons
    public List<Course> getCoursesWithMaxLessons() {
        return courseRepository.findCoursesWithMaxLessons();
    }

}
