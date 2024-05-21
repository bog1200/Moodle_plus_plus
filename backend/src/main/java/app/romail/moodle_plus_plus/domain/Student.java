package app.romail.moodle_plus_plus.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.sql.Date;
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
	@ManyToOne
	private StudentGroup group;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
	private Set<SubjectEnrollment> subjectEnrollments = new HashSet<>();

	public Student(String firstName, String lastName, String email, String phone, String address, String gender, Date dob, String studentId, Date enrollmentDate) {
		super(firstName, lastName, email, phone, address, gender, dob);
		this.studentId = studentId;
		this.enrollmentDate = enrollmentDate;
	}

	public Student() {

	}
}
