package app.romail.moodle_plus_plus.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class StudentGroup {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@OneToMany(mappedBy = "group")
	private Set<Student> students = new HashSet<>();
	@ManyToMany
	private Set<Subject> subjects = new HashSet<>();

	public StudentGroup(String name) {
		this.name = name;
	}

}
