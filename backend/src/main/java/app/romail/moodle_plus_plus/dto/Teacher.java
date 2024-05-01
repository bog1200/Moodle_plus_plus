package app.romail.moodle_plus_plus.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity

public class Teacher extends Person {
	@ManyToMany
	private Set<Subject> courses;
	private double salary;
	private String title;





}
