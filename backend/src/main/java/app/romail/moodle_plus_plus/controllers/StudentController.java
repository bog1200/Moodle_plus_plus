package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.dto.StudentDTO;
import app.romail.moodle_plus_plus.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//	@PostMapping()
//	public ResponseEntity<StudentDTO> createStudent(@RequestBody Student student) {
//
//        return null;
//    }

}
