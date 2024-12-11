package com.example.drivingschoolmanagement.controller;

import com.example.drivingschoolmanagement.model.Student;
import com.example.drivingschoolmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable Integer studentId) {
        return studentService.getStudentById(studentId);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable Integer studentId) {
        studentService.deleteStudent(studentId);
    }

    // Endpoints for procedures:

    @GetMapping("/today_lessons")
    public List<Student> getStudentsWithLessonsToday() {
        return studentService.getStudentsWithLessonsToday();
    }

    @GetMapping("/started/{year}")
    public List<Student> getStudentsStartedInYear(@PathVariable Integer year) {
        return studentService.getStudentsStartedInYear(year);
    }
}

