package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Teacher;
import app.romail.moodle_plus_plus.dto.TeacherDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import app.romail.moodle_plus_plus.domain.Subject;

import java.util.Optional;

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
		teacherDTO.setSubjects_ids(teacher.getSubjects().stream().map(Subject::getId).toList());
		return teacherDTO;
	}

}
