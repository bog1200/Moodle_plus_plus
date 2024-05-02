package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.dto.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService{

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void save(Teacher teacher) {
		em.persist(teacher);
	}

	@Override
	public void findById(Long id) {
		em.find(Teacher.class, id);
	}

}
