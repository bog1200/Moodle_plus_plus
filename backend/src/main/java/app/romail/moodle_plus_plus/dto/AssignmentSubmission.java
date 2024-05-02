package app.romail.moodle_plus_plus.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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

	@OneToOne
	private Assignment assignment;
	@OneToOne
	private Student student;
	private Date submissionDate;
	private Date gradingDate;
	private Double grade;
	private Double maxGrade;
	private String submissionText;



}
