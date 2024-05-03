package app.romail.moodle_plus_plus.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
	private List<Subject> subjects = new ArrayList<>();

	public Student(String firstName, String lastName, String email, String phone, String address, String gender, Date dob, String studentId, Date enrollmentDate) {
		super(firstName, lastName, email, phone, address, gender, dob);
		this.studentId = studentId;
		this.enrollmentDate = enrollmentDate;
	}

	public Student() {

	}
}
