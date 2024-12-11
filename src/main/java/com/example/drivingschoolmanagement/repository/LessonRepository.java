package com.example.drivingschoolmanagement.repository;

import com.example.drivingschoolmanagement.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    @Procedure(name = "lessons_today")
    List<Lesson> findLessonsToday(@Param("lessons_cursor") Class<Void> output);


    @Procedure(name = "upcoming_lessons")
    List<Lesson> findUpcomingLessonsByInstructor(@Param("p_instructor_id") Integer instructorId);

}
