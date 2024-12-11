package com.example.drivingschoolmanagement.service;

import com.example.drivingschoolmanagement.model.Instructor;
import com.example.drivingschoolmanagement.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InstructorService {
    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
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
    public List<Instructor> getInstructorsWithMaxLessonsNextWeek() {
        return instructorRepository.findInstructorsWithMaxLessonsNextWeek();
    }
    public List<Instructor> getInstructorsWithBirthdayInMonth(Integer month) {
        return instructorRepository.findInstructorsWithBirthdayInMonth(month);
    }


}
