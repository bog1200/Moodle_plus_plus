package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.dto.Student;
import app.romail.moodle_plus_plus.dto.StudentGroup;

import java.util.List;

public interface StudentGroupService {
	public void addStudentToGroup(Student student, StudentGroup studentGroup);
	public void removeStudentFromGroup(Student student, StudentGroup studentGroup);
	public void save(StudentGroup studentGroup);



}
