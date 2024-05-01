package app.romail.moodle_plus_plus.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter

public class Subject {
	@Id
	@GeneratedValue
	private Long id;
private String name;
private String description;
private String code;
@ManyToMany(mappedBy = "courses")
private Set<Teacher> teachers;
@ManyToMany(mappedBy = "subjects")
private Set<Student> students;
@OneToMany
private Set<Assignment> assignments;
@OneToMany
private Set<Course> courses;


}
