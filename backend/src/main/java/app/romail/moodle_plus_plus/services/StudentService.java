package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Student;

public interface StudentService {

	Student save(Student stud);
	Student getStudentById(Long id);
}
