package com.example.drivingschoolmanagement.service;

import com.example.drivingschoolmanagement.model.Lesson;
import com.example.drivingschoolmanagement.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class LessonService {
    private final LessonRepository lessonRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Lesson getLessonById(Integer lessonId) {
        return lessonRepository.findById(lessonId).orElse(null);
    }

    public Lesson saveLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public void deleteLesson(Integer lessonId) {
        lessonRepository.deleteById(lessonId);
    }
    public List<Lesson> getLessonsToday() {
        return lessonRepository.findLessonsToday(null);
    }

    public List<Lesson> getUpcomingLessonsByInstructor(Integer instructorId) {
        return lessonRepository.findUpcomingLessonsByInstructor(instructorId);
    }
}
