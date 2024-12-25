package com.example.drivingschoolmanagement.DTO;

import lombok.Data;

@Data
public class InstructorLessonCountDTO {
    public InstructorLessonCountDTO(String firstName2, String lastName2, Long value) {
        //TODO Auto-generated constructor stub
    }
    private String firstName;

    private String lastName;
    private int lessonCount;
}
