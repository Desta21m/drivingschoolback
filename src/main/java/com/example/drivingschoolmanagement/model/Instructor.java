package com.example.drivingschoolmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "instructors")
// @NamedStoredProcedureQuery(
//         name = "get_instructors_with_max_lessons_next_week",
//         procedureName = "get_instructors_with_max_lessons_next_week",
//         resultClasses = {Instructor.class},
//         parameters = {
//                 @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
//         }
// )
// @NamedStoredProcedureQuery(
//         name = "get_instructors_birthday_in_month",
//         procedureName = "get_instructors_birthday_in_month",
//         resultClasses = {Instructor.class},
//         parameters = {
//                 @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class),
//                 @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
//         }
// )
public class Instructor extends User{
        // @Id
        // @GeneratedValue(strategy = GenerationType.IDENTITY)
        // private Integer instructorId; // Use a single field for primary key
    
        private LocalDate hireDate;
        private double salary;
    
        @OneToMany(mappedBy = "instructor")
        @JsonIgnore
        private List<Lesson> lessons;
    
        @OneToMany(mappedBy = "instructor")
        @JsonIgnore
        private List<Test> tests;
}
