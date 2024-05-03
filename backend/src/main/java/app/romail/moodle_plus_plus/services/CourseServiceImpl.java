package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Course course) {
        em.persist(course);
    }

    @Override
    public Course findById(Long id) {
        return em.find(Course.class, id);
    }



}
