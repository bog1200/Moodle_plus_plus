package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.dto.StudentDTO;
import app.romail.moodle_plus_plus.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Long id) {
		return studentService.getById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/new")
	public ResponseEntity<URI> createStudent(@RequestBody StudentDTO studentDTO) {
		Optional<URI> uri = studentService.createStudent(studentDTO);
        return uri.<ResponseEntity<URI>>map(value -> ResponseEntity.created(value).build()).orElseGet(() -> ResponseEntity.badRequest().build());
	}

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
