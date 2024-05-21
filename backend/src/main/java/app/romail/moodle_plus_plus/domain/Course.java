package app.romail.moodle_plus_plus.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@lombok.Getter
@lombok.Setter
@AllArgsConstructor
@NoArgsConstructor

public class Course {
	@Id
	@GeneratedValue
	private Long id;
	// start and end hour of the course
	private Date startDate;
	private Date endDate;
	@ManyToOne
	private Subject subject;
	@OneToMany
	private Set<CourseAttendance> courseAttendances = new HashSet<>();

	public Course(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
}
