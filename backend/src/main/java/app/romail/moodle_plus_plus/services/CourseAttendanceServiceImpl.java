package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.CourseAttendance;
import app.romail.moodle_plus_plus.dto.CourseAttendanceDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseAttendanceServiceImpl implements CourseAttendanceService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(CourseAttendance courseAttendance) {
        em.persist(courseAttendance);
    }

    @Override
    public Optional<CourseAttendanceDTO> getById(Long id) {
        CourseAttendance courseAttendance = em.find(CourseAttendance.class, id);
        if (courseAttendance == null) {
            return Optional.empty();
        }
        return Optional.of(convertToDTO(courseAttendance));
    }

    public CourseAttendanceDTO convertToDTO(CourseAttendance courseAttendance) {
        return new CourseAttendanceDTO(courseAttendance.getId(), courseAttendance.getCourseEnrollment().getId(), courseAttendance.getDate());
    }



}
