package app.romail.moodle_plus_plus.domain;

import jakarta.persistence.*;
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
public class Assignment {
	@Id
	@GeneratedValue
	private Long id;
	private AssignmentType type;
	private String name;
	private String description;
	@OneToMany
	private List<File> files = new ArrayList<>();
	@OneToMany(mappedBy = "assignment")
	private List<AssignmentSubmission> submissions = new ArrayList<>();
	@ManyToOne
	private Subject subject;
	private Date startDate;
	private Date endDate;
	private Date deadline;
	private int maxPoints;

	public Assignment(AssignmentType type, String name, String description, Subject subject, Date startDate, Date endDate, Date deadline, int maxPoints) {
		this.type = type;
		this.name = name;
		this.description = description;
		this.subject = subject;
		this.startDate = startDate;
		this.endDate = endDate;
		this.deadline = deadline;
		this.maxPoints = maxPoints;
	}
}
