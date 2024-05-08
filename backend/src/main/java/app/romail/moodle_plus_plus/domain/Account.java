package app.romail.moodle_plus_plus.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
	@ElementCollection(fetch = FetchType.EAGER)
	@Getter
	private List<Role> roles = new ArrayList<>();

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
