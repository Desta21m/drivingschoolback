package com.example.drivingschoolmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "students")
@NamedStoredProcedureQuery(
        name = "get_students_with_lessons_today",
        procedureName = "get_students_with_lessons_today",
        resultClasses = {Student.class},
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
        }
)
@NamedStoredProcedureQuery(
        name = "get_students_started_in_year",
        procedureName = "get_students_started_in_year",
        resultClasses = {Student.class},
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class, name = "students_cursor"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "p_year")
        }
)
public class Student  extends User{
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer studentId;

    private LocalDate startDate;
    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<Lesson> lessons;
    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<Test> tests;
}