package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.CourseAttendance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class CourseAttendanceServiceImpl implements CourseAttendanceService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(CourseAttendance courseAttendance) {
        em.persist(courseAttendance);
    }

    @Override
    public CourseAttendance findById(Long id) {
        return em.find(CourseAttendance.class, id);
    }



}
