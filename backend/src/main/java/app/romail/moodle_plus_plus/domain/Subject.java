package app.romail.moodle_plus_plus.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode(exclude = {"id","teachers", "studentGroups", "assignments", "courses"})
@lombok.Getter
@lombok.Setter
@AllArgsConstructor
@NoArgsConstructor

public class Subject {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	private String code;
	@ManyToMany(mappedBy = "subjects")
	private Set<Teacher> teachers = new HashSet<>();
	@ManyToMany(mappedBy = "subjects")
	private Set<StudentGroup> studentGroups = new HashSet<>();
	@OneToMany(mappedBy = "subject")
	private Set<Assignment> assignments = new HashSet<>();
	@OneToMany(mappedBy = "subject")
	private Set<Course> courses = new HashSet<>();


	public Subject(String name, String description, String code) {
		this.name = name;
		this.description = description;
		this.code = code;
	}

}
