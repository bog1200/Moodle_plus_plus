package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.dto.IdDocument;

public interface IdDocumentService {
    void save(IdDocument idDocument);
    IdDocument findById(Long id);
}
