package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.dto.AssignmentSubmissionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import app.romail.moodle_plus_plus.services.AssignmentSubmissionService;

import java.net.URI;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/assignments/submission")
public class AssignmentSubmissionController {
    private final AssignmentSubmissionService assignmentSubmissionService;

    public AssignmentSubmissionController(AssignmentSubmissionService assignmentSubmissionService) {
        this.assignmentSubmissionService = assignmentSubmissionService;
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT')")
    @GetMapping("/{id}")
    public ResponseEntity<AssignmentSubmissionDTO> getAssignmentSubmission(@PathVariable Long id) {
        return assignmentSubmissionService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT')")
    @GetMapping("/assignment/{id}")
    public ResponseEntity<Set<AssignmentSubmissionDTO>> getAssignmentSubmissionByAssignmentId(@PathVariable Long id) {
        return ResponseEntity.ok(assignmentSubmissionService.getByAssignmentId(id));

    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyRole('STUDENT')")
    @PostMapping("/new")
    public ResponseEntity<URI> createAssignmentSubmission(@RequestBody AssignmentSubmissionDTO assignmentSubmissionDTO) {
        Optional<URI> uri = assignmentSubmissionService.createAssignmentSubmission(assignmentSubmissionDTO);
        return uri.<ResponseEntity<URI>>map(value -> ResponseEntity.created(value).build()).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasRole('STUDENT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignmentSubmission(@PathVariable Long id) {
        if (assignmentSubmissionService.deleteAssignmentSubmission(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
