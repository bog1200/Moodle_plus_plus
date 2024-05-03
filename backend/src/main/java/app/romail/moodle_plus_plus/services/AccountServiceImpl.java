package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Account;
import app.romail.moodle_plus_plus.dto.AccountDTO;
import app.romail.moodle_plus_plus.domain.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{

	@PersistenceContext
	private EntityManager em;

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

	private AccountDTO convertToDTO(Account account) {
		AccountDTO accountDTO = new AccountDTO();
		BeanUtils.copyProperties(account, accountDTO, "person");
		//accountDTO.setPerson_id(account.getPerson().getId());
		return accountDTO;
	}

	private Account convertToEntity(AccountDTO accountDTO) {
		Account account = new Account();
		account.setId(accountDTO.getId());
		account.setUsername(accountDTO.getUsername());
		return account;
	}




}