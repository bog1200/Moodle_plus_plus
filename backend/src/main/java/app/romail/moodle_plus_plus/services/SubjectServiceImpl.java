package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.*;
import app.romail.moodle_plus_plus.dto.SubjectDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.net.URI;
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

    @Transactional
    @Override
    public Optional<URI> createSubject(SubjectDTO subjectDTO) {
        Subject subject = convertToEntity(subjectDTO);
        try{
            save(subject);
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.of(URI.create("/subject/" + subject.getId()));
    }

    @Transactional
    @Override
    public boolean deleteSubject(Long id) {
        Subject subject = em.find(Subject.class, id);
        if (subject == null) {
            return false;
        }
        em.remove(subject);
        return true;
    }

    public Subject convertToEntity(SubjectDTO subjectDTO) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectDTO, subject, "teachers_ids","studentGroups_ids","assignments_ids","courses_ids");
        if (subjectDTO.getTeachers_ids()!=null) subject.setTeachers(subjectDTO.getTeachers_ids().stream().map(id -> em.find(Teacher.class, id)).toList());
        if (subjectDTO.getStudentGroups_ids()!=null) subject.setStudentGroups(subjectDTO.getStudentGroups_ids().stream().map(id -> em.find(StudentGroup.class, id)).toList());
        if (subjectDTO.getAssignments_ids()!=null) subject.setAssignments(subjectDTO.getAssignments_ids().stream().map(id -> em.find(Assignment.class, id)).toList());
        if (subjectDTO.getCourses_ids()!=null) subject.setCourses(subjectDTO.getCourses_ids().stream().map(id -> em.find(Course.class, id)).toList());
        return subject;
    }



}
