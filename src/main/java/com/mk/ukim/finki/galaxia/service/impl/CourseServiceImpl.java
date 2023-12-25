package com.mk.ukim.finki.galaxia.service.impl;

import com.mk.ukim.finki.galaxia.exceptions.CourseNotFoundException;
import com.mk.ukim.finki.galaxia.model.Course;
import com.mk.ukim.finki.galaxia.model.Lesson;
import com.mk.ukim.finki.galaxia.model.Quiz;
import com.mk.ukim.finki.galaxia.model.dto.CourseDto;
import com.mk.ukim.finki.galaxia.repository.CourseRepository;
import com.mk.ukim.finki.galaxia.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
        return this.courseRepository.findAll();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return this.courseRepository.findById(id);
    }

    @Override
    public Optional<Course> findByName(String name) {
        return this.courseRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Course> save(String name, String description, Integer duration, String imagePath) {
        this.courseRepository.deleteByName(name);
        return Optional.of(this.courseRepository.save(new Course(name, description, duration, imagePath)));
    }

    @Override
    public Optional<Course> save(CourseDto courseDto) {
        this.courseRepository.deleteByName(courseDto.getName());
        return Optional.of(this.courseRepository.save(new Course(courseDto.getName(), courseDto.getDescription(), courseDto.getDuration(), courseDto.getImagePath())));
    }

    @Override
    public Optional<Course> edit(Long id, String name, String description, Integer duration, List<Long> lessonsIds) {
        return Optional.empty();
    }

    @Override
    public Optional<Course> edit(Long id, CourseDto courseDto) {

        Course course = this.courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setDuration(courseDto.getDuration());
        course.setImagePath(courseDto.getImagePath());

        return Optional.of(this.courseRepository.save(course));

    }

    @Override
    public Course addLessonsToCourse(Long courseId, List<Lesson> lessons) {
        return null;
    }

    @Override
    public Course addLessonToCourse(Long courseId, Lesson lesson) {
        Course course = this.courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));

        lesson.setCourse(course);

        course.getLessons().add(lesson);
        return courseRepository.save(course);
    }

    @Override
    public void deleteById(Long id) {
        this.courseRepository.deleteById(id);
    }

    @Override
    public Optional<Quiz> addQuizToCourse(Long courseId, String title, String description, int totalScore) {
        return Optional.empty();
    }
}
