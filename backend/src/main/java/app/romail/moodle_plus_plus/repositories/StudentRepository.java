package app.romail.moodle_plus_plus.repositories;

import app.romail.moodle_plus_plus.dto.Person;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Person, Long> {

}
