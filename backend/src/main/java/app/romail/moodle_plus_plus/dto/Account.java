package app.romail.moodle_plus_plus.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
@lombok.Getter
public class Account {
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private Person person;
	private String username;

}
