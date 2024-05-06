package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.dto.AssignmentDTO;
import app.romail.moodle_plus_plus.services.AssignmentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {
    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentDTO> getAssignmentById(@PathVariable("id") Long id) {
        return assignmentService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/new")
    public ResponseEntity<URI> createAssignment(@RequestBody AssignmentDTO assignmentDTO) {
        Optional<URI> uri = assignmentService.createAssignment(assignmentDTO);
        return uri.<ResponseEntity<URI>>map(value -> ResponseEntity.created(value).build()).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
