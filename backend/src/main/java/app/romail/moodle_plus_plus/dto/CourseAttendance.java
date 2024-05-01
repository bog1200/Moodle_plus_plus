package app.romail.moodle_plus_plus.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
public class CourseAttendance {
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private CourseEnrollment courseEnrollment;
	private Date date;
}
