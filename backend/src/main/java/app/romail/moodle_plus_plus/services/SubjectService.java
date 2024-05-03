package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Subject;

public interface SubjectService {
    void save(Subject subject);
    Subject findById(Long id);
}
