package app.romail.moodle_plus_plus.security;

import app.romail.moodle_plus_plus.dto.Account;
import app.romail.moodle_plus_plus.repositories.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountRepoUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public AccountRepoUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account user = accountRepository.findByUsername(s);
        if(user != null)
            return user;
        throw new UsernameNotFoundException("User "+s+" not found");
    }
}
