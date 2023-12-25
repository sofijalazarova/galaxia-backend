package com.mk.ukim.finki.galaxia.repository;

import com.mk.ukim.finki.galaxia.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByName(String name);

    void deleteByName(String name);
}
