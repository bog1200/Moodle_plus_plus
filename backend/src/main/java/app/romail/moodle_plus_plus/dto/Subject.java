package app.romail.moodle_plus_plus.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
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
	@ManyToMany(mappedBy = "courses")
	private List<Teacher> teachers = new ArrayList<>();
	@ManyToMany(mappedBy = "subjects")
	private List<StudentGroup> studentGroups = new ArrayList<>();
	@OneToMany
	private List<Assignment> assignments = new ArrayList<>();
	@OneToMany
	private List<Course> courses = new ArrayList<>();


}
