package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.CourseAttendance;

public interface CourseAttendanceService {
    void save(CourseAttendance courseAttendance);
    CourseAttendance findById(Long id);
}
