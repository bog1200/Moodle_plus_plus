package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.dto.Account;

public interface AccountService {
	Account findById(Long id);
	Account findByUsername(String username);
}
