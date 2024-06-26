package app.romail.moodle_plus_plus.controllers;

import app.romail.moodle_plus_plus.domain.Account;
import app.romail.moodle_plus_plus.domain.IdDocument;
import app.romail.moodle_plus_plus.dto.AccountDTO;
import app.romail.moodle_plus_plus.dto.TotpSecretDTO;
import app.romail.moodle_plus_plus.dto.login.IdDocumentLoginDTO;
import app.romail.moodle_plus_plus.dto.JwtTokenDTO;
import app.romail.moodle_plus_plus.dto.login.ServiceLoginDTO;
import app.romail.moodle_plus_plus.dto.login.TotpLoginDTO;
import app.romail.moodle_plus_plus.repositories.AccountRepository;
import app.romail.moodle_plus_plus.security.JwtUtil;
import app.romail.moodle_plus_plus.security.SecurityAccount;
import app.romail.moodle_plus_plus.services.AccountService;
import app.romail.moodle_plus_plus.services.IdDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/account")

public class AccountController {
	private final AccountService accountService;
	private final IdDocumentService idDocumentService;
	private final JwtUtil jwtUtil;

    private final AccountRepository accountRepository;

	public AccountController(AccountService accountService, IdDocumentService idDocumentService, JwtUtil jwtUtil, AccountRepository accountRepository) {
		this.accountService = accountService;
        this.idDocumentService = idDocumentService;
        this.jwtUtil = jwtUtil;
        this.accountRepository = accountRepository;
    }

	@CrossOrigin(origins = "*")
	@PreAuthorize("hasAnyRole('STUDENT', 'TEACHER')")
	@GetMapping("/{id}")
	public ResponseEntity<AccountDTO> getAccountById(@PathVariable("id") Long id) {

		return ResponseEntity.ok(accountService.findById(id));
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/idLogin")
	public ResponseEntity<JwtTokenDTO> getAccountByIdDocument(@RequestBody IdDocumentLoginDTO idDocumentLoginDTO) {
		Optional<IdDocument> idDocumentDTO = idDocumentService.getByLogin(idDocumentLoginDTO.getCountry(), idDocumentLoginDTO.getPin());
		if (idDocumentDTO.isPresent()) {
			SecurityAccount account = new SecurityAccount(idDocumentDTO.get().getAccount());
			new UsernamePasswordAuthenticationToken(account.getUsername(), null, account.getAuthorities());
				return ResponseEntity.ok(JwtTokenDTO.builder()
						.accessToken(jwtUtil.generateToken(idDocumentDTO.get().getAccount(),false))
						.refreshToken(jwtUtil.generateToken(idDocumentDTO.get().getAccount(),true))
						.build());
			}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

	@CrossOrigin(origins = "*")
	@PostMapping("/totpLogin")
	public ResponseEntity<JwtTokenDTO> getAccountByTotp(@RequestBody TotpLoginDTO totpLoginDTO) {
		Optional<Account> account = accountService.validateTotp(totpLoginDTO.getUsername(), totpLoginDTO.getTotp());
		if (account.isPresent()) {
			SecurityAccount securityAccount = new SecurityAccount(account.get());
			new UsernamePasswordAuthenticationToken(securityAccount.getUsername(), null, securityAccount.getAuthorities());
			return ResponseEntity.ok(JwtTokenDTO.builder()
					.accessToken(jwtUtil.generateToken(account.get(),false)).build());
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/serviceLogin")
	public ResponseEntity<JwtTokenDTO> getAccountByService(@RequestBody ServiceLoginDTO serviceLoginDTO) {
		Optional<Account> account = accountService.validateService(serviceLoginDTO.getUsername(), serviceLoginDTO.getKey());
		if (account.isPresent()) {
			SecurityAccount securityAccount = new SecurityAccount(account.get());
			new UsernamePasswordAuthenticationToken(securityAccount.getUsername(), null, securityAccount.getAuthorities());
			return ResponseEntity.ok(JwtTokenDTO.builder()
					.accessToken(jwtUtil.generateToken(account.get(),false)).build());
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@CrossOrigin(origins = "*")
	@PreAuthorize("hasAnyRole('STUDENT', 'TEACHER')")
	@GetMapping("/me/accountSecret")
	public ResponseEntity<TotpSecretDTO> getAccountSecret(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {


		Long accountId = jwtUtil.extractId(token.substring(7).trim());
		Account account = accountRepository.findById(accountId).orElse(null);
		if (account == null) {
			return ResponseEntity.notFound().build();
		}
		if (jwtUtil.validateToken(token.substring(7).trim(), account.getId())) {
			return ResponseEntity.ok(new TotpSecretDTO(account.getTotpSecret()));
		}
		return ResponseEntity.notFound().build();
	}

	@CrossOrigin(origins = "*")
	@PreAuthorize("hasAnyRole('STUDENT', 'TEACHER')")
	@GetMapping("/me/refreshToken")
	public ResponseEntity<JwtTokenDTO> refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
		Long accountId = jwtUtil.extractId(token.substring(7).trim());
		Account account = accountRepository.findById(accountId).orElse(null);
		jwtUtil.validateToken(token.substring(7).trim(), accountId);
		return ResponseEntity.ok(JwtTokenDTO.builder()
				.accessToken(jwtUtil.generateToken(account,false))
				.build());
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
