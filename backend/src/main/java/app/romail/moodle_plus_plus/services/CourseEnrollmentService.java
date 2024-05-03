package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.dto.CourseEnrollment;

public interface CourseEnrollmentService {
    void save(CourseEnrollment courseEnrollment);
    CourseEnrollment findById(Long id);
}
