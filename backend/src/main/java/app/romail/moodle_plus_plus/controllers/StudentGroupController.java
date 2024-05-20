package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.dto.StudentGroupDTO;
import app.romail.moodle_plus_plus.services.StudentGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/students/group")
public class StudentGroupController {

    private final StudentGroupService studentGroupService;

    public StudentGroupController(StudentGroupService studentGroupService) {
        this.studentGroupService = studentGroupService;
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT', 'SYSTEM')")
    @GetMapping("/{id}")
    public ResponseEntity<StudentGroupDTO> getStudentGroupById(@PathVariable Long id) {
        return studentGroupService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyRole('TEACHER', 'SYSTEM')")
    @PostMapping("/new")
    public ResponseEntity<URI> createStudentGroup(@RequestBody StudentGroupDTO studentGroupDTO) {
        Optional<URI> uri = studentGroupService.createStudentGroup(studentGroupDTO);
        return uri.<ResponseEntity<URI>>map(value -> ResponseEntity.created(value).build()).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyRole('TEACHER', 'SYSTEM')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentGroup(@PathVariable Long id) {
        if (studentGroupService.deleteStudentGroup(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
