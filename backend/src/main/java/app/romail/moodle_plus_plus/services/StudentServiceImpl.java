package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Student;
import app.romail.moodle_plus_plus.dto.StudentDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
	public Optional<StudentDTO> getById(Long id) {
		Student student = em.find(Student.class, id);
		if (student == null) {
			return Optional.empty();
		}
		return Optional.of(convertToDTO(student));
	}

	@Override
	public StudentDTO convertToDTO(Student student) {
		StudentDTO studentDTO = new StudentDTO();
		BeanUtils.copyProperties(student, studentDTO, "account");
		studentDTO.setAccount_id(student.getAccount().getId());
		return studentDTO;
	}





}
