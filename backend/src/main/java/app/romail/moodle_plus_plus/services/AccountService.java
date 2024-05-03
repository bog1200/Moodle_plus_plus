package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Account;
import app.romail.moodle_plus_plus.dto.AccountDTO;

public interface AccountService {
	AccountDTO findById(Long id);
	AccountDTO findByUsername(String username);

}
