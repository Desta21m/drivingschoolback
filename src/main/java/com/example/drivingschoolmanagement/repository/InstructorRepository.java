package com.example.drivingschoolmanagement.repository;

import com.example.drivingschoolmanagement.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    @Procedure(name = "get_instructors_with_max_lessons_next_week")
    List<Instructor> findInstructorsWithMaxLessonsNextWeek();
    @Procedure(name = "get_instructors_birthday_in_month")
    List<Instructor> findInstructorsWithBirthdayInMonth(@Param("in_month") Integer month);
}
