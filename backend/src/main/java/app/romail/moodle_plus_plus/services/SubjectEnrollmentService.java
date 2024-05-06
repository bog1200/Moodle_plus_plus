package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.SubjectEnrollment;
import app.romail.moodle_plus_plus.dto.SubjectEnrollmentDTO;

import java.net.URI;
import java.util.Optional;

public interface SubjectEnrollmentService {
    void save(SubjectEnrollment subjectEnrollment);
    Optional<SubjectEnrollmentDTO> getById(Long id);
    Optional<URI> createSubjectEnrollment(SubjectEnrollmentDTO subjectEnrollmentDTO);
}
