package com.mk.ukim.finki.galaxia.service.impl;

import com.mk.ukim.finki.galaxia.model.Quiz;
import com.mk.ukim.finki.galaxia.repository.QuizRepository;
import com.mk.ukim.finki.galaxia.service.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return this.quizRepository.findAll();
    }
}
