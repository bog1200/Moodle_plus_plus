package app.romail.moodle_plus_plus.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@lombok.Getter
@lombok.Setter
@NoArgsConstructor

public class Teacher extends Person {
	@ManyToMany
	private List<Subject> courses = new ArrayList<>();
	private double salary;
	private String title;

	public Teacher(String firstName, String lastName, String email, String phone, String address, String gender, Date dob, double salary, String title) {
		super(firstName, lastName, email, phone, address, gender, dob);
		this.salary = salary;
		this.title = title;
	}







}
