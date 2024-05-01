package app.romail.moodle_plus_plus.dto;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@lombok.Getter
@lombok.Setter
public abstract class Person {
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private Account account;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String address;
	private String gender;
	private Date dob;

}
