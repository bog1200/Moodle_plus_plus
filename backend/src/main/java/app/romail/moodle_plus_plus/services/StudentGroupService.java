package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Student;
import app.romail.moodle_plus_plus.domain.StudentGroup;
import app.romail.moodle_plus_plus.dto.StudentGroupDTO;

import java.net.URI;
import java.util.Optional;

public interface StudentGroupService {
	void addStudentToGroup(Student student, StudentGroup studentGroup);
	void removeStudentFromGroup(Student student, StudentGroup studentGroup);
	void save(StudentGroup studentGroup);
    Optional<StudentGroupDTO> getById(Long id);
	Optional<URI> createStudentGroup(StudentGroupDTO studentGroupDTO);
}
