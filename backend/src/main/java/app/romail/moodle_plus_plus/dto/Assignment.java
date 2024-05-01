package app.romail.moodle_plus_plus.dto;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@lombok.Getter
@lombok.Setter
public class Assignment {
	@Id
	@GeneratedValue
	private Long id;
	private AssignmentType type;
	private String name;
	private String description;
	@OneToMany
	private Set<File> files;
	@ManyToOne
	private Subject subject;
	private Date startDate;
	private Date endDate;
	private Date deadline;
	private int maxPoints;
}
