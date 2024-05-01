package app.romail.moodle_plus_plus.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity

public class Grade {
	@Id
	@GeneratedValue
	private Long id;
	private Double grade;
	private Double maxGrade;
	private String comment;
	private String date;
	@OneToOne
	private Student student;
}
