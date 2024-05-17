package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.*;
import app.romail.moodle_plus_plus.dto.SubjectEnrollmentDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectEnrollmentServiceImpl implements SubjectEnrollmentService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(SubjectEnrollment subjectEnrollment) {
        if (subjectEnrollment.getSubject() != null) {
            subjectEnrollment.setSubject(em.find(Subject.class, subjectEnrollment.getSubject().getId()));
        }
       if (subjectEnrollment.getStudent() != null) {
           subjectEnrollment.setStudent(em.find(Student.class, subjectEnrollment.getStudent().getId()));
       }
        em.persist(subjectEnrollment);
    }

   public Optional<SubjectEnrollmentDTO> getById(Long id) {
        SubjectEnrollment subjectEnrollment = em.find(SubjectEnrollment.class, id);
        if (subjectEnrollment == null) {
            return Optional.empty();
        }
        return Optional.of(convertToDTO(subjectEnrollment));
    }

    @Transactional
    @Override
    public Optional<URI> createSubjectEnrollment(SubjectEnrollmentDTO subjectEnrollmentDTO) {
        SubjectEnrollment subjectEnrollment;
        try{
            subjectEnrollment = convertToEntity(subjectEnrollmentDTO);
            save(subjectEnrollment);

        } catch (Exception e){
            return Optional.empty();
        }
        return Optional.of(URI.create("/courseEnrollments/" + subjectEnrollment.getId()));
    }

    @Transactional
    @Override
    public boolean deleteSubjectEnrollment(Long id) {
        SubjectEnrollment subjectEnrollment = em.find(SubjectEnrollment.class, id);
        if (subjectEnrollment == null) {
            return false;
        }
        em.remove(subjectEnrollment);
        return true;
    }


    private SubjectEnrollmentDTO convertToDTO(SubjectEnrollment subjectEnrollment) {
        SubjectEnrollmentDTO subjectEnrollmentDTO = new SubjectEnrollmentDTO();
        subjectEnrollmentDTO.setId(subjectEnrollment.getId());
        subjectEnrollmentDTO.setSubject_id(subjectEnrollment.getSubject().getId());
        subjectEnrollmentDTO.setStudent_id(subjectEnrollment.getStudent().getId());
        return subjectEnrollmentDTO;
    }

    private SubjectEnrollment convertToEntity(SubjectEnrollmentDTO subjectEnrollmentDTO) {
        SubjectEnrollment subjectEnrollment = new SubjectEnrollment();
        subjectEnrollment.setId(subjectEnrollmentDTO.getId());
        subjectEnrollment.setSubject(em.find(Subject.class, subjectEnrollmentDTO.getSubject_id()));
        subjectEnrollment.setStudent(em.find(Student.class, subjectEnrollmentDTO.getStudent_id()));
        return subjectEnrollment;
    }



}
