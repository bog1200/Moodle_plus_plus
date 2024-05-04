package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.*;
import app.romail.moodle_plus_plus.dto.SubjectDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Subject subject) {
        em.persist(subject);
    }




    @Override
    public Optional<SubjectDTO> getById(Long id) {
        Subject subject = em.find(Subject.class, id);
        if (subject == null) {
            return Optional.empty();
        }
        return Optional.of(convertToDTO(subject));
    }


    public SubjectDTO convertToDTO(Subject subject) {
        SubjectDTO subjectDTO = new SubjectDTO();
        BeanUtils.copyProperties(subject, subjectDTO, "teachers","studentGroups","assignments","courses");
        subjectDTO.setTeachers_ids(subject.getTeachers().stream().map(Person::getId).toList());
        subjectDTO.setStudentGroups_ids(subject.getStudentGroups().stream().map(StudentGroup::getId).toList());
        subjectDTO.setAssignments_ids(subject.getAssignments().stream().map(Assignment::getId).toList());
        subjectDTO.setCourses_ids(subject.getCourses().stream().map(Course::getId).toList());
        return subjectDTO;
    }



}
