package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.dto.CourseAttendanceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import app.romail.moodle_plus_plus.services.CourseAttendanceService;

import java.net.URI;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/courses/attendance")
public class CourseAttendanceController {
    private final CourseAttendanceService courseAttendanceService;

    public CourseAttendanceController(CourseAttendanceService courseAttendanceService) {
        this.courseAttendanceService = courseAttendanceService;
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT','SYSTEM')")
    @GetMapping("/{id}")
    public ResponseEntity<CourseAttendanceDTO> getCourseAttendanceById(@PathVariable Long id) {
        return courseAttendanceService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT','SYSTEM')")
    @GetMapping("/course/{id}")
    public ResponseEntity<Set<CourseAttendanceDTO>> getCourseAttendancesByCourseId(@PathVariable Long id) {
        return ResponseEntity.ok(courseAttendanceService.getByCourseId(id));
    }


    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyRole('TEACHER','STUDENT','SYSTEM')")
    @GetMapping("/student/{id}")
    public ResponseEntity<Set<CourseAttendanceDTO>> getCourseAttendancesByStudentId(@PathVariable Long id) {
        return ResponseEntity.ok(courseAttendanceService.getByStudentId(id));
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyRole('SYSTEM', 'TEACHER')")
    @PostMapping("/new")
    public ResponseEntity<URI> createCourseAttendance(@RequestBody CourseAttendanceDTO courseAttendanceDTO) {
        Optional<URI> uri = courseAttendanceService.createCourseAttendance(courseAttendanceDTO);
        return uri.<ResponseEntity<URI>>map(value -> ResponseEntity.created(value).build()).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //TODO: Do we need this?
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCourseAttendance(@PathVariable Long id) {
//        if (courseAttendanceService.deleteCourseAttendance(id)) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
