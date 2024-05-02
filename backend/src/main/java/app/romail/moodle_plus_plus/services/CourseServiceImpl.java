package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.dto.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }



}
