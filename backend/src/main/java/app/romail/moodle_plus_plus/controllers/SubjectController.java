package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.dto.SubjectDTO;
import app.romail.moodle_plus_plus.services.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT')")
    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable Long id) {
        return subjectService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/new")
    public ResponseEntity<URI> createSubject(@RequestBody SubjectDTO subjectDTO) {
        Optional<URI> uri = subjectService.createSubject(subjectDTO);
        return uri.<ResponseEntity<URI>>map(value -> ResponseEntity.created(value).build()).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasRole('TEACHER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        if (subjectService.deleteSubject(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //show all courses of a teacher, using account id
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/teacher/{id}")
    public ResponseEntity<Set<SubjectDTO>> getSubjectsByTeacherId(@PathVariable Long id) {
        return ResponseEntity.ok(subjectService.getByTeacherId(id));
    }

    //show all courses of a student, using account id
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/student/{id}")
    public ResponseEntity<Set<SubjectDTO>> getSubjectsByStudentId(@PathVariable Long id) {
        return ResponseEntity.ok(subjectService.getByStudentId(id));
    }

}
