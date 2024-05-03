package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.AssignmentSubmission;

public interface AssignmentSubmissionService {
    void save(AssignmentSubmission assignmentSubmission);
    AssignmentSubmission findById(Long id);
}
