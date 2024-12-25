package com.example.drivingschoolmanagement.service;

import com.example.drivingschoolmanagement.DTO.InstructorLessonCountDTO;
import com.example.drivingschoolmanagement.model.Instructor;
import com.example.drivingschoolmanagement.model.Lesson;
import com.example.drivingschoolmanagement.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class InstructorService {
    private final InstructorRepository instructorRepository;
    private final LessonService lessonService;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository,LessonService lessonService) {
        this.instructorRepository = instructorRepository;
        this.lessonService = lessonService;
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor getInstructorById(Integer instructorId) {
        return instructorRepository.findById(instructorId).orElse(null);
    }

    public Instructor saveInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public void deleteInstructor(Integer instructorId) {
        instructorRepository.deleteById(instructorId);
    }
   public List<InstructorLessonCountDTO> getInstructorsWithMaxLessonsNextWeek() {

        List<Lesson> lessons= lessonService.getLessonsForNextWeek();

         // Group lessons by instructor and count the lessons
        Map<Instructor, Long> lessonCounts = lessons.stream()
            .collect(Collectors.groupingBy(Lesson::getInstructor, Collectors.counting()));

        // Convert the map to a list of InstructorLessonCountDTO
        return lessonCounts.entrySet().stream()
            .map(entry -> new InstructorLessonCountDTO(
                    entry.getKey().getFirstName(),  // Assuming Instructor has a getName() method
                    entry.getKey().getLastName(),
                    entry.getValue()
            ))
            .collect(Collectors.toList());
    }
    public List<Instructor> getInstructorsWithBirthdayInMonth(Integer month) {
        return instructorRepository.findInstructorsByMonthOfBirth(month);
    }


}
