package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.dto.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Student save(Student stud) {
		if (stud.getAccount().getId() == null) {
			throw new IllegalArgumentException("Account must be persisted before saving student");
		}
		em.persist(stud);
		return stud;
	}

	@Override
	public Student getStudentById(Long id) {
		return em.find(Student.class, id);
	}





}
