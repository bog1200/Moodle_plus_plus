package app.romail.moodle_plus_plus.repositories;

import app.romail.moodle_plus_plus.domain.SubjectEnrollment;
import org.springframework.data.repository.CrudRepository;

public interface CourseEnrollmentRepository extends CrudRepository<SubjectEnrollment, Long> {
}
