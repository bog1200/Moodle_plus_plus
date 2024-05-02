package app.romail.moodle_plus_plus.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@lombok.Getter
@lombok.Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseAttendance {
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private CourseEnrollment courseEnrollment;
	private Date date;
}
