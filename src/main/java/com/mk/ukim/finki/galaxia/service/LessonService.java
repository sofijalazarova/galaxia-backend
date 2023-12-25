package com.mk.ukim.finki.galaxia.service;

import com.mk.ukim.finki.galaxia.model.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonService {

    List<Lesson> getAllLessonsWithAttachments();

    List<Lesson> findAll();

    Optional<Lesson> save(Lesson lesson);
}
