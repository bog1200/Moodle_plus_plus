package app.romail.moodle_plus_plus.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
	private List<Student> students = new ArrayList<>();
	@ManyToMany
	private List<Subject> subjects = new ArrayList<>();

	public StudentGroup(String name) {
		this.name = name;
	}

}
