package com.example.drivingschoolmanagement.service;

import com.example.drivingschoolmanagement.model.Course;
import com.example.drivingschoolmanagement.model.Instructor;
import com.example.drivingschoolmanagement.model.Lesson;
import com.example.drivingschoolmanagement.model.Student;
import com.example.drivingschoolmanagement.repository.CourseRepository;
import com.example.drivingschoolmanagement.repository.InstructorRepository;
import com.example.drivingschoolmanagement.repository.LessonRepository;
import com.example.drivingschoolmanagement.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
@Transactional
public class LessonService {
    
    private final LessonRepository lessonRepository;
     private final CourseRepository courseRepository;
      private final InstructorRepository instructorRepository;
       private final StudentRepository studentRepository;


    @Autowired
    public LessonService(LessonRepository lessonRepository,CourseRepository courseRepository,InstructorRepository instructorRepository,StudentRepository studentRepository) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
        this.studentRepository = studentRepository;
    }

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Lesson getLessonById(Integer lessonId) {
        return lessonRepository.findById(lessonId).orElse(null);
    }

    public Lesson saveLesson(Lesson lesson) {
        Course c=lesson.getCourse();
        Instructor in= lesson.getInstructor();
        Student s=lesson.getStudent();
        
        s.getLessons().add(lesson);
        in.getLessons().add(lesson);
        c.getLessons().add(lesson);

        studentRepository.save(s);
        instructorRepository.save(in);
        courseRepository.save(c);
        return lessonRepository.save(lesson);
    }

    public void deleteLesson(Integer lessonId) {
        lessonRepository.deleteById(lessonId);
    }
     // Get upcoming lessons for a specific instructor
     public List<Lesson> getUpcomingLessonsByInstructor(Integer instructorId) {
        // Pass LocalDateTime.now() to query
        return lessonRepository.findUpcomingLessonsByInstructor(instructorId, LocalDateTime.now());
    }

    public List<Lesson> getLessonsForToday() {
        // Get the start and end of today
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(23, 59, 59);

        // Fetch lessons from the repository
        return lessonRepository.findLessonsForToday(startOfDay, endOfDay);
    }

    public List<Lesson> getLessonsForNextWeek() {
      // Get the current date and time
      LocalDateTime now = LocalDateTime.now();

      // Calculate the start of the next week (next Monday)
      LocalDateTime nextWeekStart = now
              .with(java.time.temporal.TemporalAdjusters.nextOrSame(java.time.DayOfWeek.MONDAY))
              .withHour(0).withMinute(0).withSecond(0).withNano(0);

      // Calculate the end of the next week (next Sunday at 23:59:59)
      LocalDateTime nextWeekEnd = nextWeekStart.plusDays(6)
              .withHour(23).withMinute(59).withSecond(59).withNano(999999999);

      // Fetch lessons for the next week
      return lessonRepository.findLessonsByDateRange(nextWeekStart, nextWeekEnd);
    }
}
