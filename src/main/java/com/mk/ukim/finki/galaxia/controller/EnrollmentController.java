package com.mk.ukim.finki.galaxia.controller;

import com.mk.ukim.finki.galaxia.auth.EnrollmentRequest;
import com.mk.ukim.finki.galaxia.repository.UserRepository;
import com.mk.ukim.finki.galaxia.service.EnrollmentService;
import com.mk.ukim.finki.galaxia.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final UserRepository userRepository;

    public EnrollmentController(EnrollmentService enrollmentService, UserRepository userRepository) {
        this.enrollmentService = enrollmentService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<Void> enrollInCourse(@RequestBody EnrollmentRequest enrollmentRequest){
        var user = this.userRepository.findByEmail(enrollmentRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found")); // Better error handling
        //System.out.println(user.getId());
        this.enrollmentService.enrollUserInCourse(user.getId(), enrollmentRequest.getCourseId());
        return ResponseEntity.ok().build();
    }
}
