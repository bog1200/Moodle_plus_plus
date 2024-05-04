package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Student;
import app.romail.moodle_plus_plus.domain.StudentGroup;
import app.romail.moodle_plus_plus.dto.StudentGroupDTO;
import jakarta.persistence.EntityManager;

import java.util.Optional;
import java.util.stream.Collectors;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class StudentGroupServiceImpl implements StudentGroupService {

	@PersistenceContext
	private EntityManager em;
	private final StudentService studentService;
	private final SubjectService subjectService;

    public StudentGroupServiceImpl(StudentService studentService, SubjectService subjectService) {
        this.studentService = studentService;
		this.subjectService = subjectService;
    }


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

	@Override
	public Optional<StudentGroupDTO> getById(Long id) {
		StudentGroup studentGroup = em.find(StudentGroup.class, id);
		if (studentGroup == null) {
			return Optional.empty();
		}
		return Optional.of(convertToDTO(studentGroup));
	}

	private StudentGroupDTO convertToDTO(StudentGroup studentGroup) {
		StudentGroupDTO studentGroupDTO = new StudentGroupDTO();
		studentGroupDTO.setId(studentGroup.getId());
		studentGroupDTO.setName(studentGroup.getName());
		studentGroupDTO.setStudents(studentGroup.getStudents().stream().map(studentService::convertToDTO).collect(Collectors.toList()));
		studentGroupDTO.setSubjects(studentGroup.getSubjects().stream().map(subjectService::convertToDTO).collect(Collectors.toList()));
		return studentGroupDTO;
	}
}
