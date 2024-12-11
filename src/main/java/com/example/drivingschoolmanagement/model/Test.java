package com.example.drivingschoolmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "tests")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "passed_tests",
                procedureName = "passed_tests",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_student_id", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
                },
                resultClasses = {Test.class}
        ),
        @NamedStoredProcedureQuery(
                name = "average_test_score",
                procedureName = "average_test_score",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_course_id", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_result", type = Double.class)
                }
        )
})
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer testId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "instructor_id")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private Instructor instructor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private Course course;

    private Integer score;

    private Boolean result;

    private LocalDate testDate;
}
