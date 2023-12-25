package com.mk.ukim.finki.galaxia.service.impl;

import com.mk.ukim.finki.galaxia.model.Lesson;
import com.mk.ukim.finki.galaxia.repository.LessonRepository;
import com.mk.ukim.finki.galaxia.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public List<Lesson> getAllLessonsWithAttachments() {
        return null;
    }

    @Override
    public List<Lesson> findAll() {
        return this.lessonRepository.findAll();
    }

    @Override
    public Optional<Lesson> save(Lesson lesson) {
        return Optional.of(this.lessonRepository.save(lesson));
    }


}
