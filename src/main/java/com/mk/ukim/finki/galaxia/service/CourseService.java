package com.mk.ukim.finki.galaxia.service;

import com.mk.ukim.finki.galaxia.model.Course;
import com.mk.ukim.finki.galaxia.model.Lesson;
import com.mk.ukim.finki.galaxia.model.Quiz;
import com.mk.ukim.finki.galaxia.model.dto.CourseDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> findAll();

    Optional<Course> findById(Long id);

    Optional<Course> findByName(String name);

    Optional<Course> save(String name, String description, Integer duration, String imageFile) throws IOException;

    Optional<Course> save(CourseDto productDto);

    Optional<Course> edit(Long id, String name, String description, Integer duration, List<Long> lessonsIds);

    Optional<Course> edit(Long id, CourseDto courseDto);

    Course addLessonsToCourse(Long courseId, List<Lesson> lessons);

    public Course addLessonToCourse(Long courseId, Lesson lesson);

    void deleteById(Long id);

    Optional<Quiz> addQuizToCourse(Long courseId, String title, String description, int totalScore);

}
