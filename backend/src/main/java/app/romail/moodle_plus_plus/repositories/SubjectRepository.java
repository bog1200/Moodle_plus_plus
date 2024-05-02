package app.romail.moodle_plus_plus.repositories;

import app.romail.moodle_plus_plus.dto.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Teacher, Long> {
}
