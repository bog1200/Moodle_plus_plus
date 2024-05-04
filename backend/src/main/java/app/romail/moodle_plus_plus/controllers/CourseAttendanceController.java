package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.dto.CourseAttendanceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import app.romail.moodle_plus_plus.services.CourseAttendanceService;

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
}
