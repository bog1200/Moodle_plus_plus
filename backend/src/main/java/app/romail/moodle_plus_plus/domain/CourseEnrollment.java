package app.romail.moodle_plus_plus.domain;

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
public class CourseEnrollment {
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private Course course;
	@OneToOne
	private Student student;
	@OneToMany
	private List<CourseAttendance> courseAttendances = new ArrayList<>();

	public CourseEnrollment(Course course, Student student) {
		this.course = course;
		this.student = student;
	}




}
