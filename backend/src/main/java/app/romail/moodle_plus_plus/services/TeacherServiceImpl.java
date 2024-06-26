package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Teacher;
import app.romail.moodle_plus_plus.dto.TeacherDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import app.romail.moodle_plus_plus.domain.Subject;

import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService{

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void save(Teacher teacher) {
		for (Subject subject : teacher.getSubjects()) {
            subject.getTeachers().add(teacher);
		}
		em.persist(teacher);
	}

	@Override
	public Optional<TeacherDTO> getById(Long id) {
		Teacher teacher = em.find(Teacher.class, id);
		if (teacher == null) {
			return Optional.empty();
		}
		return Optional.of(convertToDTO(teacher));
	}

	@Override
	public TeacherDTO convertToDTO(Teacher teacher) {
		TeacherDTO teacherDTO = new TeacherDTO();
		BeanUtils.copyProperties(teacher, teacherDTO, "account","courses");
		teacherDTO.setAccount_id(teacher.getAccount().getId());
		teacherDTO.setSubjects_ids(teacher.getSubjects().stream().map(Subject::getId).collect(Collectors.toSet()));
		return teacherDTO;
	}

	@Transactional
	@Override
	public Optional<URI> createTeacher(TeacherDTO teacherDTO) {
		Teacher teacher = convertToEntity(teacherDTO);
		try{
			save(teacher);
		} catch (Exception e) {
			return Optional.empty();
		}
		return Optional.of(URI.create("/teacher/" + teacher.getId()));
	}

	@Transactional
	@Override
	public boolean deleteTeacher(Long id) {
		Teacher teacher = em.find(Teacher.class, id);
		if (teacher == null) {
			return false;
		}
		em.remove(teacher);
		return true;
	}

	@Override
	public Teacher convertToEntity(TeacherDTO teacherDTO) {
		Teacher teacher = new Teacher();
		BeanUtils.copyProperties(teacherDTO, teacher, "account_id","courses_ids");
		teacher.setAccount(em.find(app.romail.moodle_plus_plus.domain.Account.class, teacherDTO.getAccount_id()));
		teacher.setSubjects(teacherDTO.getSubjects_ids().stream().map(id -> em.find(Subject.class, id)).collect(Collectors.toSet()));
		return teacher;
	}

}
