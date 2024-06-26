package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Account;
import app.romail.moodle_plus_plus.dto.AccountDTO;
import app.romail.moodle_plus_plus.domain.Role;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.CodeGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public AccountDTO findById(Long id) {
		return convertToDTO(em.find(Account.class, id));
	}

	@Override
	public AccountDTO findByUsername(String username) {
		return convertToDTO(em.createQuery("SELECT a FROM Account a WHERE a.username = :username", Account.class)
				.setParameter("username", username)
				.getSingleResult()
		);
	}

	@Transactional
	@Override
	public Optional<URI> createAccount(AccountDTO accountDTO) {
		Account account = convertToEntity(accountDTO);
		try {
			em.persist(account);
		} catch (Exception e) {
			return Optional.empty();
		}
		return Optional.of(URI.create("/account/" + account.getId()));
	}

	@Override
	public Optional<Account> validateTotp(String username, String totp) {
		Account account = em.createQuery("SELECT a FROM Account a WHERE a.username = :username", Account.class)
				.setParameter("username", username)
				.getSingleResult();
		if (account.getTotpSecret() == null || account.getRoles().contains(Role.ROLE_SYSTEM)) {
			return Optional.empty();
		}
		TimeProvider timeProvider = new SystemTimeProvider();
		CodeGenerator codeGenerator = new DefaultCodeGenerator();
		CodeVerifier verifier = new DefaultCodeVerifier(codeGenerator, timeProvider);
		if (verifier.isValidCode(account.getTotpSecret(), totp)) {
			return Optional.of(account);
		}
		return Optional.empty();
	}

	@Override
	public Optional<Account> validateService(String username, String password) {
		Account account = em.createQuery("SELECT a FROM Account a WHERE a.username = :username", Account.class)
				.setParameter("username", username)
				.getSingleResult();
		if (account.getRoles().contains(Role.ROLE_SYSTEM) && passwordEncoder.matches(password, account.getPassword())) {
			return Optional.of(account);
		}
		return Optional.empty();
	}

	private AccountDTO convertToDTO(Account account) {
		AccountDTO accountDTO = new AccountDTO();
		BeanUtils.copyProperties(account, accountDTO, "person");
		accountDTO.setPerson_id(account.getPerson().getId());
		accountDTO.setRoles(account.getRoles().stream().map(Role::toString).collect(Collectors.toSet()));
		return accountDTO;
	}

	private Account convertToEntity(AccountDTO accountDTO) {
		Account account = new Account();
		account.setId(accountDTO.getId());
		account.setUsername(accountDTO.getUsername());
		return account;
	}




}
