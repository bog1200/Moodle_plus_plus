package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Course;
import app.romail.moodle_plus_plus.domain.CourseAttendance;
import app.romail.moodle_plus_plus.domain.Subject;
import app.romail.moodle_plus_plus.dto.CourseDTO;
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
public class CourseServiceImpl implements CourseService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Course course) {
      Subject subject = course.getSubject();
    if (subject != null) {
        subject.getCourses().add(course);
    }

    Set<CourseAttendance> courseAttendance = course.getCourseAttendances();
    if (courseAttendance != null) {
        for (CourseAttendance ca : courseAttendance) {
            ca.setCourse(course);
        }
    }

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

    @Transactional
    @Override
    public Optional<URI> createCourse(CourseDTO courseDTO) {
        Course course;
        try{
            course = convertToEntity(courseDTO);
            save(course);

        } catch (Exception e){
            return Optional.empty();
        }
        return Optional.of(URI.create("/course/" + course.getId()));
    }

    @Transactional
    @Override
    public boolean deleteCourse(Long id) {
        Course course = em.find(Course.class, id);
        if (course == null) {
            return false;
        }
        em.remove(course);
        return true;
    }

    @Override
    public Set<CourseDTO> getBySubjectId(Long id) {
        Subject subject = em.find(Subject.class, id);
        if (subject == null) {
            return Set.of();
        }
        return subject.getCourses().stream().map(this::convertToDTO).collect(Collectors.toSet());
    }


    public CourseDTO convertToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setSubject_id(course.getSubject().getId());
        courseDTO.setStartDate(course.getStartDate().getTime());
        courseDTO.setEndDate(course.getEndDate().getTime());
        courseDTO.setCourseAttendances_ids(course.getCourseAttendances().stream().map(CourseAttendance::getId).collect(Collectors.toSet()));
        return courseDTO;
    }

    private Course convertToEntity(CourseDTO courseDTO) {
        Course course = new Course();
        course.setId(courseDTO.getId());
        course.setSubject(em.find(Subject.class, courseDTO.getSubject_id()));
        course.setStartDate(new Date(courseDTO.getStartDate()));
        course.setEndDate(new Date(courseDTO.getEndDate()));
        return course;
    }



}
