package app.romail.moodle_plus_plus.security;

import app.romail.moodle_plus_plus.domain.Account;
import app.romail.moodle_plus_plus.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account user = accountRepository.findById(Long.parseLong(s));
        if(user != null)
           return new SecurityAccount(user);
        throw new UsernameNotFoundException("User "+s+" not found");
    }
}
