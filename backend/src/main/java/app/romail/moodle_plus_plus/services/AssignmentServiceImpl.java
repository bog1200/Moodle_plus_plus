package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.dto.Assignment;
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



}
