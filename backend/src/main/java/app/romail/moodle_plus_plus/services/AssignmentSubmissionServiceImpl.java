package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.dto.AssignmentSubmission;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class AssignmentSubmissionServiceImpl implements AssignmentSubmissionService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(AssignmentSubmission assignmentSubmission) {
        em.persist(assignmentSubmission);
    }

    @Override
    public AssignmentSubmission findById(Long id) {
        return em.find(AssignmentSubmission.class, id);
    }



}
