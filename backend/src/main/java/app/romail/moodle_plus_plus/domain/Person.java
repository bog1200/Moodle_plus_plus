package app.romail.moodle_plus_plus.domain;

import jakarta.persistence.*;

import java.sql.Date;

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

	public Person(String firstName, String lastName, String email, String phone, String address, String gender, Date dob) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.gender = gender;
		this.dob = dob;
	}

	public Person() {

	}
}
