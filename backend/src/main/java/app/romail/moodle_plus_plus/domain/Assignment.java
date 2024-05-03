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
	@ManyToOne
	private Subject subject;
	private Date startDate;
	private Date endDate;
	private Date deadline;
	private int maxPoints;
}
