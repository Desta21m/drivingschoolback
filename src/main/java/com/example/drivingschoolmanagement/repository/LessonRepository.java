package com.example.drivingschoolmanagement.repository;

import com.example.drivingschoolmanagement.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    // @Procedure(name = "lessons_today")
    // List<Lesson> findLessonsToday(@Param("lessons_cursor") Class<Void> output);


    // @Procedure(name = "upcoming_lessons")
    // List<Lesson> findUpcomingLessonsByInstructor(@Param("p_instructor_id") Integer instructorId);

    @Query("SELECT l FROM Lesson l WHERE l.startTime BETWEEN :startOfNextWeek AND :endOfNextWeek")
    List<Lesson> findLessonsByDateRange(
        @Param("startOfNextWeek") LocalDateTime startOfNextWeek,
        @Param("endOfNextWeek") LocalDateTime endOfNextWeek
    );

    // Query to get upcoming lessons by instructor with start time after now
    @Query("SELECT l FROM Lesson l WHERE l.instructor.id = :instructorId AND l.startTime > :now")
    List<Lesson> findUpcomingLessonsByInstructor(Integer instructorId, LocalDateTime now);
    
    @Query("SELECT l FROM Lesson l WHERE l.startTime BETWEEN :startOfDay AND :endOfDay")
     List<Lesson> findLessonsForToday(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);

}
