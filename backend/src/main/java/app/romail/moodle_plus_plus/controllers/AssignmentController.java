package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.dto.AssignmentDTO;
import app.romail.moodle_plus_plus.services.AssignmentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/assignment")
public class AssignmentController {
    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT')")
    @GetMapping("/{id}")
    public ResponseEntity<AssignmentDTO> getAssignmentById(@PathVariable("id") Long id) {
        return assignmentService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT')")
    @GetMapping("/subject/{id}")
    public ResponseEntity<Set<AssignmentDTO>> getAssignmentsBySubjectId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(assignmentService.findBySubjectId(id));
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/new")
    public ResponseEntity<URI> createAssignment(@RequestBody AssignmentDTO assignmentDTO) {
        Optional<URI> uri = assignmentService.createAssignment(assignmentDTO);
        return uri.<ResponseEntity<URI>>map(value -> ResponseEntity.created(value).build()).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasRole('TEACHER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable("id") Long id) {
        if (assignmentService.deleteAssignment(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
