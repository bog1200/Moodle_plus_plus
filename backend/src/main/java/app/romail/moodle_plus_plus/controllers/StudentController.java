package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.dto.StudentDTO;
import app.romail.moodle_plus_plus.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@PreAuthorize("hasAnyRole('TEACHER', 'STUDENT', 'SYSTEM' )")
	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Long id) {
		return studentService.getById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PreAuthorize("hasRole('SYSTEM')")
	@PostMapping("/new")
	public ResponseEntity<URI> createStudent(@RequestBody StudentDTO studentDTO) {
		Optional<URI> uri = studentService.createStudent(studentDTO);
        return uri.<ResponseEntity<URI>>map(value -> ResponseEntity.created(value).build()).orElseGet(() -> ResponseEntity.badRequest().build());
	}

	@PreAuthorize("hasRole('SYSTEM')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
		if (studentService.deleteStudent(id)) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

//	@PostMapping()
//	public ResponseEntity<StudentDTO> createStudent(@RequestBody Student student) {
//
//        return null;
//    }

}
