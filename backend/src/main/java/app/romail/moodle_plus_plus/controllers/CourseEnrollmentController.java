package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.dto.CourseEnrollmentDTO;
import app.romail.moodle_plus_plus.services.CourseEnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses/enrollment")
public class CourseEnrollmentController {
    private final CourseEnrollmentService courseEnrollmentService;

    public CourseEnrollmentController(CourseEnrollmentService courseEnrollmentService) {
        this.courseEnrollmentService = courseEnrollmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseEnrollmentDTO> getCourseEnrollmentById(@PathVariable Long id) {
        return courseEnrollmentService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
