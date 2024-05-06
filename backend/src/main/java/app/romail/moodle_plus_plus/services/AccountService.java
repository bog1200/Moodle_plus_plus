package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Account;
import app.romail.moodle_plus_plus.dto.AccountDTO;

import java.net.URI;
import java.util.Optional;

public interface AccountService {
	AccountDTO findById(Long id);
	AccountDTO findByUsername(String username);

	Optional<URI> createAccount(AccountDTO accountDTO);
}
