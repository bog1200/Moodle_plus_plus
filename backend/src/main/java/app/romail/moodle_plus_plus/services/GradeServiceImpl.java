package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Grade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Grade grade) {
        em.persist(grade);
    }

    @Override
    public Grade findById(Long id) {
        return em.find(Grade.class, id);
    }



}
