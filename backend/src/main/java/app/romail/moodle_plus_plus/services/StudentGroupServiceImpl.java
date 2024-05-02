package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.dto.Student;
import app.romail.moodle_plus_plus.dto.StudentGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class StudentGroupServiceImpl implements StudentGroupService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addStudentToGroup(Student student, StudentGroup studentGroup) {
		studentGroup.getStudents().add(student);
		student.setGroup(studentGroup);
		entityManager.persist(studentGroup);
		entityManager.persist(student);

	}

	@Override
	public void removeStudentFromGroup(Student student, StudentGroup studentGroup) {
		studentGroup.getStudents().remove(student);
		student.setGroup(null);
		entityManager.persist(studentGroup);
		entityManager.persist(student);
	}

	@Override
	public void save(StudentGroup studentGroup) {
		entityManager.persist(studentGroup);


	}
}
