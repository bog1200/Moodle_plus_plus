package app.romail.moodle_plus_plus.repositories;

import app.romail.moodle_plus_plus.domain.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    Optional<Teacher> findByAccountUsername(String username);
}
