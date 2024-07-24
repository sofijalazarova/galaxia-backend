package com.mk.ukim.finki.galaxia.controller;

import com.mk.ukim.finki.galaxia.model.Course;
import com.mk.ukim.finki.galaxia.model.FileDb;
import com.mk.ukim.finki.galaxia.model.Lesson;
import com.mk.ukim.finki.galaxia.model.Quiz;
import com.mk.ukim.finki.galaxia.model.dto.CourseDto;
import com.mk.ukim.finki.galaxia.service.CourseService;
import com.mk.ukim.finki.galaxia.service.FileStorageService;
import com.mk.ukim.finki.galaxia.service.LessonService;
import com.mk.ukim.finki.galaxia.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
//@CrossOrigin(origins = "*")
public class CourseController {

    private final CourseService courseService;
    private final LessonService lessonService;
    private final FileStorageService fileStorageService;
    private final QuizService quizService;

    private static final List<Course> courses = List.of(
            new Course("Astronomy Basics", "Astronomy Basics desc", 10, "https://img.freepik.com/foto-gratis/fondo-espacial-polvo-estrellas-estrellas-brillantes-cosmos-colorido-realista-nebulosa-via-lactea_1258-150914.jpg?size=338&ext=jpg"),
            new Course("Advanced Astrophysics", "Advanced Astrophysics desc", 12, "https://img.freepik.com/foto-gratis/fondo-espacial-polvo-estrellas-estrellas-brillantes-cosmos-colorido-realista-nebulosa-via-lactea_1258-150914.jpg?size=338&ext=jpg")
    );

    @GetMapping("listcourses")
    public List<Course> findAllCourses(){
        return courses;
    }

    @GetMapping("courses")
    public ResponseEntity<List<Course>> getCourses() {
        try {
            return new ResponseEntity<>(courseService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id){
        return this.courseService.findById(id)
                .map(course -> ResponseEntity.ok().body(course))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity deleteCourse(@PathVariable Long id){
        this.courseService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Course> save(@RequestBody CourseDto courseDto){
        System.out.println(courseDto.getName());
        return this.courseService.save(courseDto)
                .map(course -> ResponseEntity.ok().body(course))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Course> edit(@PathVariable Long id, @RequestBody CourseDto courseDto){
        return this.courseService.edit(id, courseDto)
                .map(course -> ResponseEntity.ok().body(course))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //@CrossOrigin(allowedHeaders = "*")
    @PostMapping("/addLesson/{id}")
    public ResponseEntity<Course> addLessonToCourse(@PathVariable Long id, @RequestBody Lesson lesson){

        this.lessonService.save(lesson);
        Course updatedCourse =  this.courseService.addLessonToCourse(id, lesson);
        return ResponseEntity.ok(updatedCourse);
    }

    @PostMapping("/addLessonWithFiles/{id}")
    public ResponseEntity<Course> addLessonToCourseWithFiles(
            @PathVariable Long id,
            @RequestParam("attachments") List<MultipartFile> files,
            @RequestBody Lesson lesson){

        try {
            List<FileDb> uploadedFiles = new ArrayList<>();

            for (MultipartFile file : files) {
                FileDb uploadedFile = this.fileStorageService.store(file);
                uploadedFile.setLesson(lesson);
                uploadedFiles.add(uploadedFile);
            }

            for (FileDb uploadedFile : uploadedFiles) {
                System.out.println("Uploaded File " + uploadedFile.getName());
            }

            lesson.setAttachments(uploadedFiles);
            Course updatedCourse = this.courseService.addLessonToCourse(id, lesson);

            return ResponseEntity.ok(updatedCourse);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/quizzes")
    public List<Quiz> getQuizzes(){
        return this.quizService.getAllQuizzes();
    }
}
