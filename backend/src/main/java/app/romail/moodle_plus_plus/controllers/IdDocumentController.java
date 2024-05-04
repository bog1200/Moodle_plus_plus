package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.dto.IdDocumentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import app.romail.moodle_plus_plus.services.IdDocumentService;

@RestController
@RequestMapping("/idDocument")
public class IdDocumentController {
    private final IdDocumentService idDocumentService;

    public IdDocumentController(IdDocumentService idDocumentService) {
        this.idDocumentService = idDocumentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdDocumentDTO> getIdDocument(@PathVariable Long id) {
        return idDocumentService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
