package com.example.drivingschoolmanagement.repository;

import com.example.drivingschoolmanagement.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Procedure(name = "get_courses_with_lessons_today")
    List<Course> findCoursesWithLessonsToday();
    @Procedure(name = "get_courses_with_max_lessons")
    List<Course> findCoursesWithMaxLessons();
}
