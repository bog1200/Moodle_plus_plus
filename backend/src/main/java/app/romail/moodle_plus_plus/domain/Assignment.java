package app.romail.moodle_plus_plus.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.sql.Date;
import java.util.Set;

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
//	@OneToMany
//	private Set<File> files = new HashSet<>();
	@OneToMany(mappedBy = "assignment")
	private Set<AssignmentSubmission> submissions = new HashSet<>();
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
