package com.example.drivingschoolmanagement.repository;

import com.example.drivingschoolmanagement.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Integer> {
    // Find passed tests by a student
    @Query("SELECT t FROM Test t WHERE t.student.id = :studentId AND t.result = true")
    List<Test> findPassedTestsByStudent(Integer studentId);

    // Calculate average test score for a course
    @Query("SELECT AVG(t.score) FROM Test t WHERE t.course.courseId = :courseId")
    Double findAverageTestScoreByCourse(Integer courseId);
}
