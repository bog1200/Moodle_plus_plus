package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Student;
import app.romail.moodle_plus_plus.dto.StudentDTO;

import java.util.Optional;

public interface StudentService {

	StudentDTO save(Student stud);
	Optional<StudentDTO> getById(Long id);
	StudentDTO convertToDTO(Student student);
}
