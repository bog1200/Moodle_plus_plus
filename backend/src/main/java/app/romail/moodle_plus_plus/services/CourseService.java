package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Course;
import app.romail.moodle_plus_plus.dto.CourseDTO;

import java.util.Optional;

public interface CourseService {
    void save(Course course);
    Optional<CourseDTO> getById(Long id);
}
