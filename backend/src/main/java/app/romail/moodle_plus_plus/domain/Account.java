package app.romail.moodle_plus_plus.domain;

import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class Account{
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private Person person;
	private String username;
	private String password;
	private String totpSecret = new DefaultSecretGenerator().generate();
	@ElementCollection(fetch = FetchType.EAGER)
	@Getter
	private Set<Role> roles = new HashSet<>();

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
