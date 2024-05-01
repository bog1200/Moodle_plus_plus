package app.romail.moodle_plus_plus.dto;

import jakarta.persistence.*;

@Entity

public class File {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String path;
	private String type;
	private String description;
	@OneToOne
	private Assignment assignment;
}
