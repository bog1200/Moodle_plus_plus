package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Student;
import app.romail.moodle_plus_plus.domain.StudentGroup;

public interface StudentGroupService {
	public void addStudentToGroup(Student student, StudentGroup studentGroup);
	public void removeStudentFromGroup(Student student, StudentGroup studentGroup);
	public void save(StudentGroup studentGroup);



}
