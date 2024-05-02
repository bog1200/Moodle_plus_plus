package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.dto.Teacher;

public interface TeacherService {
	void save(Teacher teacher);
	void findById(Long id);
}
