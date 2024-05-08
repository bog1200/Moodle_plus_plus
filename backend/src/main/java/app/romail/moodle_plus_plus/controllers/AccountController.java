package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.domain.IdDocument;
import app.romail.moodle_plus_plus.dto.AccountDTO;
import app.romail.moodle_plus_plus.dto.JwtTokenDTO;
import app.romail.moodle_plus_plus.security.JwtUtil;
import app.romail.moodle_plus_plus.security.SecurityAccount;
import app.romail.moodle_plus_plus.services.AccountService;
import app.romail.moodle_plus_plus.services.IdDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/account")

public class AccountController {
	private final AccountService accountService;
	@Autowired
	private IdDocumentService idDocumentService;
	private final JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	public AccountController(AccountService accountService, JwtUtil jwtUtil) {
		this.accountService = accountService;
		this.jwtUtil = jwtUtil;
	}

	@PreAuthorize("hasAnyRole('STUDENT', 'TEACHER')")
	@GetMapping("/{id}")
	public ResponseEntity<AccountDTO> getAccountById(@PathVariable("id") Long id) {

		return ResponseEntity.ok(accountService.findById(id));
	}

	@PostMapping("/idLogin")
	public ResponseEntity<JwtTokenDTO> getAccountByIdDocument(@RequestParam String country, @RequestParam String pin) {
		Optional<IdDocument> idDocumentDTO = idDocumentService.getByLogin(country, pin);
		if (idDocumentDTO.isPresent()) {
			SecurityAccount account = new SecurityAccount(idDocumentDTO.get().getAccount());
			new UsernamePasswordAuthenticationToken(account.getUsername(), null, account.getAuthorities());
				return ResponseEntity.ok(JwtTokenDTO.builder()
						.accessToken(jwtUtil.generateToken(idDocumentDTO.get().getAccount().getUsername())).build());
			}
		return ResponseEntity.notFound().build();
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
