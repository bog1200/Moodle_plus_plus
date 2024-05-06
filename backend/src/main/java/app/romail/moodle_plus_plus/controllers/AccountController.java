package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.dto.AccountDTO;
import app.romail.moodle_plus_plus.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/account")

public class AccountController {
	private final AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountDTO> getAccountById(@PathVariable("id") Long id) {

		return ResponseEntity.ok(accountService.findById(id));
	}

//	@PostMapping("/new")
//	public ResponseEntity<URI> createAccount(@RequestBody AccountDTO accountDTO) {
//		Optional<URI> uri = accountService.createAccount(accountDTO);
//		if (uri.isPresent()) {
//			return ResponseEntity.created(uri.get()).build();
//		} else {
//			return ResponseEntity.badRequest().build();
//		}
//	}



}
