package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Student;
import app.romail.moodle_plus_plus.domain.StudentGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class StudentGroupServiceImpl implements StudentGroupService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void addStudentToGroup(Student student, StudentGroup studentGroup) {
		studentGroup.getStudents().add(student);
		student.setGroup(studentGroup);
		em.persist(studentGroup);
		em.persist(student);

	}

	@Override
	public void removeStudentFromGroup(Student student, StudentGroup studentGroup) {
		studentGroup.getStudents().remove(student);
		student.setGroup(null);
		em.persist(studentGroup);
		em.persist(student);
	}

	@Override
	public void save(StudentGroup studentGroup) {
		em.persist(studentGroup);


	}
}
