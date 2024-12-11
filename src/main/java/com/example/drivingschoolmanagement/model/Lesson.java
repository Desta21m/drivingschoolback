package com.example.drivingschoolmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "lessons")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "lessons_today",
                procedureName = "lessons_today",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "lessons_cursor", type = void.class)
                },
                resultClasses = {Lesson.class}
        ),
        @NamedStoredProcedureQuery(
                name = "upcoming_lessons",
                procedureName = "upcoming_lessons",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_instructor_id", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
                },
                resultClasses = {Lesson.class}
        )
})
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lessonId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "assignment_id")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private VehicleAssignment vehicleAssignment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private Course course;

    @ManyToOne(optional = false)
    @JoinColumn(name = "instructor_id")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private Instructor instructor;

    private LocalDate lessonDate;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
