package com.example.drivingschoolmanagement.service;

import com.example.drivingschoolmanagement.model.Course;
import com.example.drivingschoolmanagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Course> getCoursesWithLessonsToday() {
        return courseRepository.findCoursesWithLessonsToday();
    }
    public List<Course> getCoursesWithMaxLessons() {
        return courseRepository.findCoursesWithMaxLessons();
    }

}
