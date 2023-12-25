package com.mk.ukim.finki.galaxia.controller;

import com.mk.ukim.finki.galaxia.auth.EnrollmentRequest;
import com.mk.ukim.finki.galaxia.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<Void> enrollInCourse(EnrollmentRequest enrollmentRequest){
        this.enrollmentService.enrollUserInCourse(enrollmentRequest.getUserId(), enrollmentRequest.getCourseId());
        return ResponseEntity.ok().build();
    }
}
