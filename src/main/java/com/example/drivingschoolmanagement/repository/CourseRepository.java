package com.example.drivingschoolmanagement.repository;

import com.example.drivingschoolmanagement.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    // @Procedure(name = "get_courses_with_lessons_today")
    // List<Course> findCoursesWithLessonsToday();
    // @Procedure(name = "get_courses_with_max_lessons")
    // List<Course> findCoursesWithMaxLessons();

        // Method to get courses with lessons scheduled for today
    @Query("SELECT DISTINCT c FROM Course c JOIN c.lessons l WHERE l.startTime BETWEEN :startOfDay AND :endOfDay")
    List<Course> findCoursesWithLessonsToday(LocalDateTime startOfDay, LocalDateTime endOfDay);

    // Method to get courses with the maximum number of lessons
    @Query("SELECT c FROM Course c JOIN c.lessons l GROUP BY c.courseId ORDER BY COUNT(l) DESC")
    List<Course> findCoursesWithMaxLessons();
}
