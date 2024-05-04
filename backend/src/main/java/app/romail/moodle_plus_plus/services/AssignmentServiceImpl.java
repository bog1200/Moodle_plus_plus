package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Assignment;
import app.romail.moodle_plus_plus.domain.AssignmentSubmission;
import app.romail.moodle_plus_plus.domain.File;
import app.romail.moodle_plus_plus.dto.AssignmentDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Assignment assignment) {
        em.persist(assignment);
    }

    @Override
    public Optional<AssignmentDTO> findById(Long id) {
        Assignment assignment = em.find(Assignment.class, id);
        if (assignment == null) {
            return Optional.empty();
        }
        return Optional.of(convertToDTO(assignment));
    }


    private AssignmentDTO convertToDTO(Assignment assignment) {
        AssignmentDTO assignmentDTO = new AssignmentDTO();
        BeanUtils.copyProperties(assignment, assignmentDTO, "files", "submissions", "subject", "type");
        assignmentDTO.setType(assignment.getType().toString());
        assignmentDTO.setSubject_id(assignment.getSubject().getId());
        assignmentDTO.setFiles_ids(assignment.getFiles().stream().map(File::getId).toList());
        assignmentDTO.setSubmissions_ids(assignment.getSubmissions().stream().map(AssignmentSubmission::getId).toList());
        return assignmentDTO;
    }
}
