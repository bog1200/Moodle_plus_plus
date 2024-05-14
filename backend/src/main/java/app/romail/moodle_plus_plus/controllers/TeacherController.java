package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.dto.TeacherDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import app.romail.moodle_plus_plus.services.TeacherService;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT', 'SYSTEM')")
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long id) {
        Optional<TeacherDTO> teacher = teacherService.getById(id);
        return teacher.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('SYSTEM')")
    @PostMapping("/new")
    public ResponseEntity<URI> createTeacher(@RequestBody TeacherDTO teacherDTO) {
        Optional<URI> uri = teacherService.createTeacher(teacherDTO);
        return uri.<ResponseEntity<URI>>map(value -> ResponseEntity.created(value).build()).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PreAuthorize("hasRole('SYSTEM')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        if (teacherService.deleteTeacher(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
