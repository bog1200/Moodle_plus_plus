package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.dto.AssignmentSubmissionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.romail.moodle_plus_plus.services.AssignmentSubmissionService;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/assignments/submission")
public class AssignmentSubmissionController {
    private final AssignmentSubmissionService assignmentSubmissionService;

    public AssignmentSubmissionController(AssignmentSubmissionService assignmentSubmissionService) {
        this.assignmentSubmissionService = assignmentSubmissionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentSubmissionDTO> getAssignmentSubmission(@PathVariable Long id) {
        return assignmentSubmissionService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/new")
    public ResponseEntity<URI> createAssignmentSubmission(@RequestBody AssignmentSubmissionDTO assignmentSubmissionDTO) {
        Optional<URI> uri = assignmentSubmissionService.createAssignmentSubmission(assignmentSubmissionDTO);
        return uri.<ResponseEntity<URI>>map(value -> ResponseEntity.created(value).build()).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
