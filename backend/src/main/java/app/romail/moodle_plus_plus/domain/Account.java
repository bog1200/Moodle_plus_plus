package app.romail.moodle_plus_plus.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
public class Account implements UserDetails{
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	@JsonSerialize
	private Person person;
	private String username;
	private String password;
	@ElementCollection(fetch = FetchType.EAGER)
	@Getter
	private List<Role> roles = new ArrayList<>();

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Account() {

	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for(Role role : roles)
			authorities.add(new SimpleGrantedAuthority(role.toString()));
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
