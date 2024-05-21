package app.romail.moodle_plus_plus.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@lombok.Getter
@lombok.Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseAttendance {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Course course;
	@ManyToOne
	private SubjectEnrollment subjectEnrollment;
	private Timestamp date;

	public CourseAttendance(SubjectEnrollment subjectEnrollment, Timestamp date) {
		this.subjectEnrollment = subjectEnrollment;
		this.date = date;
	}
}
