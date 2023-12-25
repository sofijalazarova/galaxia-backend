package com.mk.ukim.finki.galaxia.exceptions;

public class QuizNotFoundException extends RuntimeException{

    public QuizNotFoundException(Long id){
        super(String.format("Course with id: %d was not found", id));
    }
}
