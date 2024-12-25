package com.example.drivingschoolmanagement.service;

import com.example.drivingschoolmanagement.model.Lesson;
import com.example.drivingschoolmanagement.model.Student;
import com.example.drivingschoolmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {
    private final StudentRepository studentRepository;
    private final LessonService lessonService;

    @Autowired
    public StudentService(StudentRepository studentRepository,LessonService lessonService) {
        this.studentRepository = studentRepository;
        this.lessonService = lessonService;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Integer studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Integer studentId) {
        studentRepository.deleteById(studentId);
    }
    public List<Student> getStudentsWithLessonsToday() {
        // Fetch today's lessons
        List<Lesson> lessonsForToday = lessonService.getLessonsForToday();

        // Extract and collect unique students from the lessons
        return lessonsForToday.stream()
                              .map(Lesson::getStudent)
                              .distinct() // Ensure no duplicates
                              .collect(Collectors.toList());
    }
    public List<Student> getStudentsStartedInYear(Integer year) {
        return studentRepository.findStudentsStartedInYears(year);
    }


}
