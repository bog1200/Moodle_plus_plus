package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.dto.CourseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import app.romail.moodle_plus_plus.services.CourseService;

import java.net.URI;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT')")
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        return courseService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT')")
    @GetMapping("/getBySubject/{id}")
    public ResponseEntity<Set<CourseDTO>> getCourseBySubjectId(@PathVariable Long id) {
        Set<CourseDTO> courses = courseService.getBySubjectId(id);
        return ResponseEntity.ok(courses);
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/new")
    public ResponseEntity<URI> createCourse(@RequestBody CourseDTO courseDTO) {
        Optional<URI> uri = courseService.createCourse(courseDTO);
        return uri.<ResponseEntity<URI>>map(value -> ResponseEntity.created(value).build()).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PreAuthorize("hasRole('TEACHER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        if (courseService.deleteCourse(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    



}
