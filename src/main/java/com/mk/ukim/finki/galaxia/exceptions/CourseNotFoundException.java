package com.mk.ukim.finki.galaxia.exceptions;

public class CourseNotFoundException extends RuntimeException{

    public CourseNotFoundException(Long id){
        super(String.format("Course with id: %d was not found", id));
    }
}
