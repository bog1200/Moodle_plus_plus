package app.romail.moodle_plus_plus.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
	private List<CourseAttendance> courseAttendances = new ArrayList<>();

	public Course(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
}
