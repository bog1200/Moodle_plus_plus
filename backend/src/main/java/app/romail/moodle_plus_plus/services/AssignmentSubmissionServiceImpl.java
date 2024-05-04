package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.AssignmentSubmission;
import app.romail.moodle_plus_plus.dto.AssignmentSubmissionDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssignmentSubmissionServiceImpl implements AssignmentSubmissionService {

    @PersistenceContext
    private EntityManager em;

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

    public AssignmentSubmissionDTO convertToDTO(AssignmentSubmission assignmentSubmission) {
        AssignmentSubmissionDTO assignmentSubmissionDTO = new AssignmentSubmissionDTO();
        BeanUtils.copyProperties(assignmentSubmission, assignmentSubmissionDTO, "assignment","student","grade");
        assignmentSubmissionDTO.setAssignment_id(assignmentSubmission.getAssignment().getId());
        assignmentSubmissionDTO.setStudent_id(assignmentSubmission.getStudent().getId());
        assignmentSubmissionDTO.setGrade_id(assignmentSubmission.getGrade().getId());
        return assignmentSubmissionDTO;
    }





}
