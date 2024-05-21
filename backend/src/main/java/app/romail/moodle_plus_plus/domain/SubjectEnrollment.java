package app.romail.moodle_plus_plus.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@lombok.Getter
@lombok.Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(uniqueConstraints={
		@UniqueConstraint(columnNames = {"subject_id", "student_id"})
})
public class SubjectEnrollment {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Subject subject;
	@ManyToOne
	private Student student;
	@OneToMany
	private Set<CourseAttendance> courseAttendances = new HashSet<>();

	public SubjectEnrollment(Subject subject, Student student) {
		this.subject = subject;
		this.student = student;
	}




}
