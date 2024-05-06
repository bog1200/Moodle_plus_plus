package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.dto.CourseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.romail.moodle_plus_plus.services.CourseService;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        return courseService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/new")
    public ResponseEntity<URI> createCourse(@RequestBody CourseDTO courseDTO) {
        Optional<URI> uri = courseService.createCourse(courseDTO);
        return uri.<ResponseEntity<URI>>map(value -> ResponseEntity.created(value).build()).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        if (courseService.deleteCourse(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    



}
