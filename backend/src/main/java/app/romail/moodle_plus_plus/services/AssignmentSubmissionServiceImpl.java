package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Assignment;
import app.romail.moodle_plus_plus.domain.AssignmentSubmission;
import app.romail.moodle_plus_plus.domain.Grade;
import app.romail.moodle_plus_plus.domain.Student;
import app.romail.moodle_plus_plus.dto.AssignmentSubmissionDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.sql.Date;
import java.util.Optional;

@Service
public class AssignmentSubmissionServiceImpl implements AssignmentSubmissionService {
    @PersistenceContext
    private EntityManager em;


    @Transactional
    @Override
    public void save(AssignmentSubmission assignmentSubmission) {
        em.persist(assignmentSubmission);
    }

    @Override
    public Optional<AssignmentSubmissionDTO> getById(Long id) {
        AssignmentSubmission assignmentSubmission = em.find(AssignmentSubmission.class, id);
        if (assignmentSubmission == null) {
            return Optional.empty();
        }
        return Optional.of(convertToDTO(assignmentSubmission));
    }

    @Transactional
    @Override
    public Optional<URI> createAssignmentSubmission(AssignmentSubmissionDTO assignmentSubmissionDTO) {
        AssignmentSubmission assignmentSubmission;
        try{
            assignmentSubmission = convertToEntity(assignmentSubmissionDTO);
            save(assignmentSubmission);

        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.of(URI.create("/assignments/submission/" + assignmentSubmission.getId()));
    }

    @Transactional
    @Override
    public boolean deleteAssignmentSubmission(Long id) {
        AssignmentSubmission assignmentSubmission = em.find(AssignmentSubmission.class, id);
        if (assignmentSubmission == null) {
            return false;
        }
        em.remove(assignmentSubmission);
        return true;
    }

    private AssignmentSubmissionDTO convertToDTO(AssignmentSubmission assignmentSubmission) {
        AssignmentSubmissionDTO assignmentSubmissionDTO = new AssignmentSubmissionDTO();
        BeanUtils.copyProperties(assignmentSubmission, assignmentSubmissionDTO, "assignment","student","grade");
        assignmentSubmissionDTO.setAssignment_id(assignmentSubmission.getAssignment().getId());
        assignmentSubmissionDTO.setStudent_id(assignmentSubmission.getStudent().getId());
        assignmentSubmissionDTO.setGrade_id(assignmentSubmission.getGrade().getId());
        return assignmentSubmissionDTO;
    }

    private AssignmentSubmission convertToEntity(AssignmentSubmissionDTO assignmentSubmissionDTO) {
        AssignmentSubmission assignmentSubmission = new AssignmentSubmission();
        BeanUtils.copyProperties(assignmentSubmissionDTO, assignmentSubmission, "assignment_id","student_id","grade_id");
        assignmentSubmission.setAssignment(em.find(Assignment.class, assignmentSubmissionDTO.getAssignment_id()));
        assignmentSubmission.setStudent(em.find(Student.class, assignmentSubmissionDTO.getStudent_id()));
        if (assignmentSubmissionDTO.getGrade_id() != null){
            assignmentSubmission.setGrade(em.find(Grade.class, assignmentSubmissionDTO.getGrade_id()));
        }
        assignmentSubmission.setSubmissionDate(new Date(assignmentSubmissionDTO.getSubmissionDate()));
        return assignmentSubmission;
    }





}
