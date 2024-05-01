package app.romail.moodle_plus_plus.dto;

import jakarta.persistence.*;

import java.util.Set;

@Entity

public class CourseEnrollment {
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private Course course;
	@OneToOne
	private Student student;
	@OneToMany
	private Set<CourseAttendance> courseAttendances;

//	private Set<Grade> grades;




}
