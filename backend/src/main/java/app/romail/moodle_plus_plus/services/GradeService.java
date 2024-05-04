package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Grade;
import app.romail.moodle_plus_plus.dto.GradeDTO;

import java.util.Optional;

public interface GradeService {
    void save(Grade grade);
    Optional<GradeDTO> getById(Long id);
}
