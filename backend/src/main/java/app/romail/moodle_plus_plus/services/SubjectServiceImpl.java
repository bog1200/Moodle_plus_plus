package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Subject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Subject subject) {
        em.persist(subject);
    }

    @Override
    public Subject findById(Long id) {
        return em.find(Subject.class, id);
    }



}
