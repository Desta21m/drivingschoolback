package com.example.drivingschoolmanagement.service;

import com.example.drivingschoolmanagement.model.Student;
import com.example.drivingschoolmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
        return studentRepository.findStudentsWithLessonsToday();
    }
    public List<Student> getStudentsStartedInYear(Integer year) {
        return studentRepository.findStudentsStartedInYear(year);
    }


}
