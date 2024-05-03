package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.domain.Student;
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
	public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(studentService.getStudentById(id));
	}

	@PostMapping()
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		return ResponseEntity.ok(studentService.save(student));
	}

}
