package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Course;
import app.romail.moodle_plus_plus.domain.CourseAttendance;
import app.romail.moodle_plus_plus.domain.Subject;
import app.romail.moodle_plus_plus.domain.SubjectEnrollment;
import app.romail.moodle_plus_plus.dto.CourseAttendanceDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.sql.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseAttendanceServiceImpl implements CourseAttendanceService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(CourseAttendance courseAttendance) {
        Course course = courseAttendance.getCourse();
        if (course != null) {
            course.getCourseAttendances().add(courseAttendance);

        }
        SubjectEnrollment subjectEnrollment = courseAttendance.getSubjectEnrollment();
        if (subjectEnrollment != null) {
            subjectEnrollment.getCourseAttendances().add(courseAttendance);
        }
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

    @Override
    public Set<CourseAttendanceDTO> getByCourseId(Long id) {
        Course course = em.find(Course.class, id);
        if (course == null) {
           return Set.of();
        }
        return course.getCourseAttendances().stream().map(this::convertToDTO).collect(Collectors.toSet());
    }

    public CourseAttendanceDTO convertToDTO(CourseAttendance courseAttendance) {
        CourseAttendanceDTO courseAttendanceDTO = new CourseAttendanceDTO();
        courseAttendanceDTO.setId(courseAttendance.getId());
        courseAttendanceDTO.setCourse_id(courseAttendance.getCourse().getId());
        courseAttendanceDTO.setSubjectEnrollment_id(courseAttendance.getSubjectEnrollment().getId());
        courseAttendanceDTO.setDate(courseAttendance.getDate().getTime());
        return courseAttendanceDTO;
    }

    private CourseAttendance convertToEntity(CourseAttendanceDTO courseAttendanceDTO) {
        CourseAttendance courseAttendance = new CourseAttendance();
        courseAttendance.setId(courseAttendanceDTO.getId());
        courseAttendance.setCourse(em.find(Course.class, courseAttendanceDTO.getCourse_id()));
        courseAttendance.setSubjectEnrollment(em.find(SubjectEnrollment.class, courseAttendanceDTO.getSubjectEnrollment_id()));
        courseAttendance.setDate(new Date(courseAttendanceDTO.getDate()));
        return courseAttendance;
    }



}
