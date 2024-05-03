package app.romail.moodle_plus_plus.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
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
	private Date startDate;
	private Date endDate;
	@OneToMany
	private List<CourseAttendance> courseAttendances = new ArrayList<>();
	private Double maxGrade;
}
