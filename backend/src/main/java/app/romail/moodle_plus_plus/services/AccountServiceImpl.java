package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.dto.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Account findById(Long id) {
		return entityManager.find(Account.class, id);
	}

	@Override
	public Account findByUsername(String username) {
		return entityManager.createQuery("SELECT a FROM Account a WHERE a.username = :username", Account.class)
				.setParameter("username", username)
				.getSingleResult();
	}




}
