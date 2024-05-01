package app.romail.moodle_plus_plus.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;


@Setter
@Getter
@Entity
public class Student extends Person {
	@Id
	@GeneratedValue
	private Long id;
	private String studentId;
	private Date enrollmentDate;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private StudentGroup group;
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Subject> subjects;






}
