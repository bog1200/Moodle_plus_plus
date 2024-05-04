package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.AssignmentSubmission;
import app.romail.moodle_plus_plus.dto.AssignmentSubmissionDTO;

import java.util.Optional;

public interface AssignmentSubmissionService {
    void save(AssignmentSubmission assignmentSubmission);
    Optional<AssignmentSubmissionDTO> getById(Long id);
}
