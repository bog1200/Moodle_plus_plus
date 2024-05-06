package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.IdDocument;
import app.romail.moodle_plus_plus.dto.IdDocumentDTO;

import java.net.URI;
import java.util.Optional;

public interface IdDocumentService {
    void save(IdDocument idDocument);
    Optional<IdDocumentDTO> getById(Long id);
    Optional<URI> createIdDocument(IdDocumentDTO idDocumentDTO);

    boolean deleteIdDocument(Long id);
}
