package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.CourseAttendance;
import app.romail.moodle_plus_plus.domain.SubjectEnrollment;
import app.romail.moodle_plus_plus.dto.CourseAttendanceDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.sql.Date;
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

    @Transactional
    @Override
    public Optional<URI> createCourseAttendance(CourseAttendanceDTO courseAttendanceDTO) {
        CourseAttendance courseAttendance;
        try{
            courseAttendance = convertToEntity(courseAttendanceDTO);
            save(courseAttendance);

        } catch (Exception e){
            return Optional.empty();
        }
        return Optional.of(URI.create("/courses/attendance/" + courseAttendance.getId()));
    }

    @Transactional
    @Override
    public boolean deleteCourseAttendance(Long id) {
        CourseAttendance courseAttendance = em.find(CourseAttendance.class, id);
        if (courseAttendance == null) {
            return false;
        }
        em.remove(courseAttendance);
        return true;
    }

    public CourseAttendanceDTO convertToDTO(CourseAttendance courseAttendance) {
        return new CourseAttendanceDTO(courseAttendance.getId(), courseAttendance.getSubjectEnrollment().getId(), courseAttendance.getDate().getTime());
    }

    private CourseAttendance convertToEntity(CourseAttendanceDTO courseAttendanceDTO) {
        CourseAttendance courseAttendance = new CourseAttendance();
        courseAttendance.setId(courseAttendanceDTO.getId());
        courseAttendance.setSubjectEnrollment(em.find(SubjectEnrollment.class, courseAttendanceDTO.getSubjectEnrollment_id()));
        courseAttendance.setDate(new Date(courseAttendanceDTO.getDate()));
        return courseAttendance;
    }



}
