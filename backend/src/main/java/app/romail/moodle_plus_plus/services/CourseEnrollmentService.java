package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.CourseEnrollment;
import app.romail.moodle_plus_plus.dto.CourseEnrollmentDTO;

import java.util.Optional;

public interface CourseEnrollmentService {
    void save(CourseEnrollment courseEnrollment);
    Optional<CourseEnrollmentDTO> getById(Long id);
}
