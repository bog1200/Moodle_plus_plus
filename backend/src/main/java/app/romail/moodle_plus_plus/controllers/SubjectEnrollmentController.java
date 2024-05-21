package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.dto.SubjectEnrollmentDTO;
import app.romail.moodle_plus_plus.services.SubjectEnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/subjects/enrollment")
public class SubjectEnrollmentController {
    private final SubjectEnrollmentService subjectEnrollmentService;

    public SubjectEnrollmentController(SubjectEnrollmentService subjectEnrollmentService) {
        this.subjectEnrollmentService = subjectEnrollmentService;
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT')")
    @GetMapping("/{id}")
    public ResponseEntity<SubjectEnrollmentDTO> getSubjectEnrollmentById(@PathVariable Long id) {
        return subjectEnrollmentService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER')")
    @PostMapping("/new")
    public ResponseEntity<URI> createSubjectEnrollment(@RequestBody SubjectEnrollmentDTO subjectEnrollmentDTO) {
        Optional<URI> uri = subjectEnrollmentService.createSubjectEnrollment(subjectEnrollmentDTO);
        return uri.<ResponseEntity<URI>>map(value -> ResponseEntity.created(value).build()).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubjectEnrollment(@PathVariable Long id) {
        if (subjectEnrollmentService.deleteSubjectEnrollment(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
