package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Student;
import app.romail.moodle_plus_plus.dto.StudentDTO;

public interface StudentService {

	StudentDTO save(Student stud);
	StudentDTO getStudentById(Long id);
}
