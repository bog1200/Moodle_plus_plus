package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Account;
import app.romail.moodle_plus_plus.domain.Student;
import app.romail.moodle_plus_plus.dto.StudentDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.sql.Date;
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
		BeanUtils.copyProperties(student, studentDTO, "account","dob","enrollmentDate");
		studentDTO.setAccount_id(student.getAccount().getId());
		studentDTO.setDob(student.getDob().getTime());
		studentDTO.setEnrollmentDate(student.getEnrollmentDate().getTime());
		return studentDTO;
	}

	@Transactional
	@Override
	public Optional<URI> createStudent(StudentDTO studentDTO) {
		Student student = convertToEntity(studentDTO);
		try {
			save(student);
		} catch (Exception e) {
			return Optional.empty();
		}
		return Optional.of(URI.create("/student/" + student.getId()));
	}

	@Transactional
	@Override
	public boolean deleteStudent(Long id) {
		Student student = em.find(Student.class, id);
		if (student == null) {
			return false;
		}
		em.remove(student);
		return true;
	}

	public Student convertToEntity(StudentDTO studentDTO) {
		Student student = new Student();
		BeanUtils.copyProperties(studentDTO, student, "account_id","dob","enrollmentDate");
		student.setAccount(em.find(Account.class, studentDTO.getAccount_id()));
		student.setDob(new Date(studentDTO.getDob()));
		student.setEnrollmentDate(new Date(studentDTO.getEnrollmentDate()));
		return student;
	}





}
