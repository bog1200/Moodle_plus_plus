package app.romail.moodle_plus_plus.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.Set;

@Entity

public class Course {
	@Id
	@GeneratedValue
	private Long id;
	private Date startDate;
	private Date endDate;
	@OneToMany
	private Set<CourseAttendance> courseAttendances;
	private Double maxGrade;
}
