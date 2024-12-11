package com.example.drivingschoolmanagement.repository;

import com.example.drivingschoolmanagement.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Integer> {
    @Procedure(name = "passed_tests")
    List<Test> findPassedTestsByStudent(@Param("p_student_id") Integer studentId);

    @Procedure(name = "average_test_score")
    Double findAverageTestScoreByCourse(@Param("p_course_id") Integer courseId);
}
