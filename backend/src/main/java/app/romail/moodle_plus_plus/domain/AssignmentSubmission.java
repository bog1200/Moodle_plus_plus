package app.romail.moodle_plus_plus.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@lombok.Getter
@lombok.Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentSubmission {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Assignment assignment;
	@OneToOne
	private Student student;
	private Date submissionDate;
	@OneToOne
	private Grade grade;
	private String submissionText;

	public AssignmentSubmission(Assignment assignment, Student student, Date submissionDate, String submissionText) {
		this.assignment = assignment;
		this.student = student;
		this.submissionDate = submissionDate;
		this.submissionText = submissionText;
	}



}
