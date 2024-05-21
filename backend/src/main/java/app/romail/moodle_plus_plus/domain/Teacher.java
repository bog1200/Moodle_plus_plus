package app.romail.moodle_plus_plus.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.sql.Date;
import java.util.Set;

@Entity
@lombok.Getter
@lombok.Setter
@NoArgsConstructor

public class Teacher extends Person {
	@ManyToMany
	private Set<Subject> subjects = new HashSet<>();
	private double salary;
	private String title;

	public Teacher(String firstName, String lastName, String email, String phone, String address, String gender, Date dob, double salary, String title) {
		super(firstName, lastName, email, phone, address, gender, dob);
		this.salary = salary;
		this.title = title;
	}







}
