package com.example.drivingschoolmanagement.repository;

import com.example.drivingschoolmanagement.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    @Procedure(name = "get_instructors_birthday_in_month")
    List<Instructor> findInstructorsWithBirthdayInMonth(@Param("in_month") Integer month);

    @Query("SELECT i FROM Instructor i WHERE MONTH(i.dateOfBirth) = :month")
    List<Instructor> findInstructorsByMonthOfBirth(int month);
    

}
