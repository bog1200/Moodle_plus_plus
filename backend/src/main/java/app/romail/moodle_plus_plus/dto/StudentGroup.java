package app.romail.moodle_plus_plus.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter

public class StudentGroup {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@OneToMany
	private Set<Student> students;
	@ManyToMany
	private Set<Subject> subjects;



}
