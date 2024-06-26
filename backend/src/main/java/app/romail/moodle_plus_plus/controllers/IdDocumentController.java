package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.dto.IdDocumentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import app.romail.moodle_plus_plus.services.IdDocumentService;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/idDocument")
public class IdDocumentController {
    private final IdDocumentService idDocumentService;

    public IdDocumentController(IdDocumentService idDocumentService) {
        this.idDocumentService = idDocumentService;
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT','SYSTEM')")
    @GetMapping("/{id}")
    public ResponseEntity<IdDocumentDTO> getIdDocument(@PathVariable Long id) {
        return idDocumentService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasRole('SYSTEM')")
        @PostMapping("/new")
        public ResponseEntity<URI> createIdDocument(@RequestBody IdDocumentDTO idDocumentDTO) {
            Optional<URI> uri = idDocumentService.createIdDocument(idDocumentDTO);
            return uri.<ResponseEntity<URI>>map(value -> ResponseEntity.created(value).build()).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasRole('SYSTEM')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIdDocument(@PathVariable Long id) {
        if (idDocumentService.deleteIdDocument(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
