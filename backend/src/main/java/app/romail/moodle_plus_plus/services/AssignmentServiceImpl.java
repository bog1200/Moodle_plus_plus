package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Assignment;
import app.romail.moodle_plus_plus.domain.CourseEnrollment;
import app.romail.moodle_plus_plus.dto.AssignmentDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Assignment assignment) {
        em.persist(assignment);
    }

    @Override
    public Assignment findById(Long id) {
        return em.find(Assignment.class, id);
    }

    private Assignment convertToEntity(AssignmentDTO assignmentDTO) {
        Assignment assignment = new Assignment();
        return assignment;
    }

    private AssignmentDTO convertToDTO(Assignment assignment) {
        AssignmentDTO assignmentDTO = new AssignmentDTO();
        return assignmentDTO;
    }



}
