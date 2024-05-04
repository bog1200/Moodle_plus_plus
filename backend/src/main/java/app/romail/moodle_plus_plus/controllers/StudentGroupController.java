package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.dto.StudentGroupDTO;
import app.romail.moodle_plus_plus.services.StudentGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students/group")
public class StudentGroupController {

    private final StudentGroupService studentGroupService;

    public StudentGroupController(StudentGroupService studentGroupService) {
        this.studentGroupService = studentGroupService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentGroupDTO> getStudentGroupById(@PathVariable Long id) {
        return studentGroupService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
