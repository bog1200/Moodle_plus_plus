package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Student;
import app.romail.moodle_plus_plus.dto.StudentDTO;

import java.net.URI;
import java.util.Optional;

public interface StudentService {

	StudentDTO save(Student stud);
	Optional<StudentDTO> getById(Long id);
	StudentDTO convertToDTO(Student student);
	Optional<URI> createStudent(StudentDTO studentDTO);

	Student convertToEntity(StudentDTO studentDTO);

    boolean deleteStudent(Long id);

    Optional<StudentDTO> getByEnrollmentId(Long id);
}
