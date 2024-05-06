package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Subject;
import app.romail.moodle_plus_plus.dto.SubjectDTO;

import java.net.URI;
import java.util.Optional;

public interface SubjectService {
    void save(Subject subject);
    Optional<SubjectDTO> getById(Long id);

    SubjectDTO convertToDTO(Subject subject);
    Optional<URI> createSubject(SubjectDTO subjectDTO);
    Subject convertToEntity(SubjectDTO subjectDTO);
}
