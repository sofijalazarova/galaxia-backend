package com.mk.ukim.finki.galaxia.service.impl;

import com.mk.ukim.finki.galaxia.exceptions.CourseNotFoundException;
import com.mk.ukim.finki.galaxia.model.Course;
import com.mk.ukim.finki.galaxia.model.Enrollment;
import com.mk.ukim.finki.galaxia.repository.CourseRepository;
import com.mk.ukim.finki.galaxia.repository.EnrollmentRepository;
import com.mk.ukim.finki.galaxia.repository.UserRepository;
import com.mk.ukim.finki.galaxia.service.EnrollmentService;
import com.mk.ukim.finki.galaxia.user.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentServiceImpl(UserRepository userRepository, CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public void enrollUserInCourse(Long userId, Long courseId) {
        User user = this.userRepository.findById(userId).orElseThrow();
        Course course = this.courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));

        if(course == null){
            throw new IllegalArgumentException("Invalid course");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);

        enrollmentRepository.save(enrollment);
    }
}
