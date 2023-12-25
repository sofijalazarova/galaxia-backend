package com.mk.ukim.finki.galaxia.model.dto;

import lombok.Data;

@Data
public class CourseDto {

    private String name;

    private String description;

    private Integer duration;

    private String imagePath;

    public CourseDto() {
    }

    public CourseDto(String name, String description, Integer duration, String imagePath) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.imagePath = imagePath;
    }
}
