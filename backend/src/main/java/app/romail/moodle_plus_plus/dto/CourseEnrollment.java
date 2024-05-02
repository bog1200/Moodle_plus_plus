package app.romail.moodle_plus_plus.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
	private List<CourseAttendance> courseAttendances;

//	private List<Grade> grades;




}
