package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.dto.Course;

public interface CourseService {
    void save(Course course);
    Course findById(Long id);
}
