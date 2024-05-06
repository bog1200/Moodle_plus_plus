package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Teacher;
import app.romail.moodle_plus_plus.dto.TeacherDTO;

import java.net.URI;
import java.util.Optional;

public interface TeacherService {
	void save(Teacher teacher);
    Optional<TeacherDTO> getById(Long id);
	TeacherDTO convertToDTO(Teacher teacher);
	Optional<URI> createTeacher(TeacherDTO teacherDTO);

	Teacher convertToEntity(TeacherDTO teacherDTO);
}
