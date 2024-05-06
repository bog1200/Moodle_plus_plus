package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Assignment;
import app.romail.moodle_plus_plus.dto.AssignmentDTO;

import java.net.URI;
import java.util.Optional;

public interface AssignmentService {
    void save(Assignment assignment);
    Optional<AssignmentDTO> findById(Long id);
    Optional<URI> createAssignment(AssignmentDTO assignmentDTO);
}
