package app.romail.moodle_plus_plus.repositories;

import app.romail.moodle_plus_plus.dto.File;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<File, Long> {
}
