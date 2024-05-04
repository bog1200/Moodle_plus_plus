package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Course;
import app.romail.moodle_plus_plus.domain.CourseAttendance;
import app.romail.moodle_plus_plus.dto.CourseDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Course course) {
        em.persist(course);
    }

    @Override
    public Optional<CourseDTO> getById(Long id) {
        Course course = em.find(Course.class, id);
        if (course == null) {
            return Optional.empty();
        }
        return Optional.of(convertToDTO(course));
    }

    public CourseDTO convertToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setSubject_id(course.getSubject().getId());
        courseDTO.setStartDate(course.getStartDate().toString());
        courseDTO.setEndDate(course.getEndDate().toString());
        courseDTO.setCourseAttendances_ids(course.getCourseAttendances().stream().map(CourseAttendance::getId).toList());
        return courseDTO;
    }



}
