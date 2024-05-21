package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.*;
import app.romail.moodle_plus_plus.dto.SubjectDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void save(Subject subject) {
        for (Teacher teacher : subject.getTeachers()) {
            teacher.getSubjects().add(subject);

        }
        for (StudentGroup studentGroup : subject.getStudentGroups()) {
            studentGroup.getSubjects().add(subject);
        }
        for (Assignment assignment : subject.getAssignments()) {
           if (assignment.getSubject() == null) {
               assignment.setSubject(subject);
           }
        }
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

    @Override
    public Set<SubjectDTO> getByTeacherId(Long id) {
        //There are multiple teachers for a subject, so we need to use a join query
        List<Subject> subjects = em.createQuery("SELECT s FROM Subject s JOIN s.teachers t WHERE t.id = :id", Subject.class)
                .setParameter("id", id)
                .getResultList();
        return subjects.stream().map(this::convertToDTO).collect(Collectors.toSet());
    }

    @Override
    public Set<SubjectDTO> getByStudentId(Long id) {
        //There are multiple student groups for a subject, so we need to use a join query
        List<Subject> subjects = em.createQuery("SELECT s FROM SubjectEnrollment se JOIN se.subject s WHERE se.student.id = :id", Subject.class)
                .setParameter("id", id)
                .getResultList();
        return subjects.stream().map(this::convertToDTO).collect(Collectors.toSet());
    }


    public SubjectDTO convertToDTO(Subject subject) {
        SubjectDTO subjectDTO = new SubjectDTO();
        BeanUtils.copyProperties(subject, subjectDTO, "teachers","studentGroups","assignments","courses");
        subjectDTO.setTeachers_ids(subject.getTeachers().stream().map(Person::getId).collect((Collectors.toSet())));
        subjectDTO.setStudentGroups_ids(subject.getStudentGroups().stream().map(StudentGroup::getId).collect(Collectors.toSet()));
        subjectDTO.setAssignments_ids(subject.getAssignments().stream().map(Assignment::getId).collect(Collectors.toSet()));
        subjectDTO.setCourses_ids(subject.getCourses().stream().map(Course::getId).collect(Collectors.toSet()));
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
        if (subjectDTO.getTeachers_ids()!=null) subject.setTeachers(subjectDTO.getTeachers_ids().stream().map(id -> em.find(Teacher.class, id)).collect(Collectors.toSet()));
        if (subjectDTO.getStudentGroups_ids()!=null) subject.setStudentGroups(subjectDTO.getStudentGroups_ids().stream().map(id -> em.find(StudentGroup.class, id)).collect(Collectors.toSet()));
        if (subjectDTO.getAssignments_ids()!=null) subject.setAssignments(subjectDTO.getAssignments_ids().stream().map(id -> em.find(Assignment.class, id)).collect(Collectors.toSet()));
        if (subjectDTO.getCourses_ids()!=null) subject.setCourses(subjectDTO.getCourses_ids().stream().map(id -> em.find(Course.class, id)).collect(Collectors.toSet()));
        return subject;
    }



}
