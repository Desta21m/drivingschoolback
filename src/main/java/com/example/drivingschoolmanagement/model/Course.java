package com.example.drivingschoolmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name = "courses")
@NamedStoredProcedureQuery(
        name = "get_courses_with_lessons_today",
        procedureName = "get_courses_with_lessons_today",
        resultClasses = {Course.class},
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
        }
)
@NamedStoredProcedureQuery(
        name = "get_courses_with_max_lessons",
        procedureName = "get_courses_with_max_lessons",
        resultClasses = {Course.class},
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
        }
)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    private String courseName;

    private String courseDescription;

    private Integer courseDuration;

    private BigDecimal courseFee;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<Test> tests;
}
