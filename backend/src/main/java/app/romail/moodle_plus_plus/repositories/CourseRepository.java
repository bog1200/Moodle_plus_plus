package app.romail.moodle_plus_plus.repositories;

import app.romail.moodle_plus_plus.dto.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
