package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.dto.Assignment;

public interface AssignmentService {
    void save(Assignment assignment);
    Assignment findById(Long id);
}
