package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Student;
import app.romail.moodle_plus_plus.dto.StudentDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public StudentDTO save(Student stud) {
		if (stud.getAccount().getId() == null) {
			throw new IllegalArgumentException("Account must be persisted before saving student");
		}
		em.persist(stud);
		return convertToDTO(stud);
	}

	@Override
	public StudentDTO getStudentById(Long id) {
		return convertToDTO(em.find(Student.class, id));
	}

	private StudentDTO convertToDTO(Student student) {
		StudentDTO studentDTO = new StudentDTO();
		BeanUtils.copyProperties(student, studentDTO, "account");
		studentDTO.setAccount_id(student.getAccount().getId());
		return studentDTO;
	}





}
