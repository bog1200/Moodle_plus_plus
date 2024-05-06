package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.CourseAttendance;
import app.romail.moodle_plus_plus.dto.CourseAttendanceDTO;

import java.net.URI;
import java.util.Optional;

public interface CourseAttendanceService {
    void save(CourseAttendance courseAttendance);
    Optional<CourseAttendanceDTO> getById(Long id);
    Optional<URI> createCourseAttendance(CourseAttendanceDTO courseAttendanceDTO);
}
