package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.dto.CourseEnrollment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class CourseEnrollmentServiceImpl implements CourseEnrollmentService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(CourseEnrollment courseEnrollment) {
        em.persist(courseEnrollment);
    }

    @Override
    public CourseEnrollment findById(Long id) {
        return em.find(CourseEnrollment.class, id);
    }



}
