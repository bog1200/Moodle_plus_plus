package app.romail.moodle_plus_plus.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Entity
@lombok.Getter
@lombok.Setter
@NoArgsConstructor
public class AssignmentSubmission {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Assignment assignment;
	@ManyToOne
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
