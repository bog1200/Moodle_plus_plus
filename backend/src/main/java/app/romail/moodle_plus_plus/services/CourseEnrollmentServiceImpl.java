package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.CourseAttendance;
import app.romail.moodle_plus_plus.domain.CourseEnrollment;
import app.romail.moodle_plus_plus.dto.CourseEnrollmentDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CourseEnrollmentServiceImpl implements CourseEnrollmentService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(CourseEnrollment courseEnrollment) {
        em.persist(courseEnrollment);
    }

   public Optional<CourseEnrollmentDTO> getById(Long id) {
        CourseEnrollment courseEnrollment = em.find(CourseEnrollment.class, id);
        if (courseEnrollment == null) {
            return Optional.empty();
        }
        return Optional.of(convertToDTO(courseEnrollment));
    }

    public CourseEnrollmentDTO convertToDTO(CourseEnrollment courseEnrollment) {
        CourseEnrollmentDTO courseEnrollmentDTO = new CourseEnrollmentDTO();
        courseEnrollmentDTO.setId(courseEnrollment.getId());
        courseEnrollmentDTO.setCourse_id(courseEnrollment.getCourse().getId());
        courseEnrollmentDTO.setStudent_id(courseEnrollment.getStudent().getId());
        courseEnrollmentDTO.setCourseAttendances_ids(courseEnrollment.getCourseAttendances().stream().map(CourseAttendance::getId).toList());
        return courseEnrollmentDTO;
    }



}
