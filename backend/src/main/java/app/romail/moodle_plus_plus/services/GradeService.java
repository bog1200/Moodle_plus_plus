package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.dto.Grade;

public interface GradeService {
    void save(Grade grade);
    Grade findById(Long id);
}
