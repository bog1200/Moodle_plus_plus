package app.romail.moodle_plus_plus.repositories;

import app.romail.moodle_plus_plus.domain.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
}
