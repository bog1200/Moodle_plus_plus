package app.romail.moodle_plus_plus.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;

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
	private SubjectEnrollment subjectEnrollment;
	private Date date;

	public CourseAttendance(SubjectEnrollment subjectEnrollment, Date date) {
		this.subjectEnrollment = subjectEnrollment;
		this.date = date;
	}
}
