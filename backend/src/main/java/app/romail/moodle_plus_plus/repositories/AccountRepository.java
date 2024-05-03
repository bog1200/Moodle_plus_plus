package app.romail.moodle_plus_plus.repositories;

import app.romail.moodle_plus_plus.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

	Account findById(long id);
	Account findByUsername(String username);

}
