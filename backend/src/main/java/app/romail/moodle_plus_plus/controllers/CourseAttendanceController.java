package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.dto.CourseAttendanceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.romail.moodle_plus_plus.services.CourseAttendanceService;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/courses/attendance")
public class CourseAttendanceController {
    private final CourseAttendanceService courseAttendanceService;

    public CourseAttendanceController(CourseAttendanceService courseAttendanceService) {
        this.courseAttendanceService = courseAttendanceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseAttendanceDTO> getCourseAttendanceById(@PathVariable Long id) {
        return courseAttendanceService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/new")
    public ResponseEntity<URI> createCourseAttendance(@RequestBody CourseAttendanceDTO courseAttendanceDTO) {
        Optional<URI> uri = courseAttendanceService.createCourseAttendance(courseAttendanceDTO);
        return uri.<ResponseEntity<URI>>map(value -> ResponseEntity.created(value).build()).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
