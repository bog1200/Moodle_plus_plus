package app.romail.moodle_plus_plus.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter


public class Grade {
	@Id
	@GeneratedValue
	private Long id;
	private Double grade;
	private Double maxGrade;
	private String comment;
	private String date;
	@OneToOne
	private AssignmentSubmission assignmentSubmission;

	public Grade(Double grade, Double maxGrade, String comment, String date, AssignmentSubmission assignmentSubmission) {
		this.grade = grade;
		this.maxGrade = maxGrade;
		this.comment = comment;
		this.date = date;
		this.assignmentSubmission = assignmentSubmission;
	}
}
