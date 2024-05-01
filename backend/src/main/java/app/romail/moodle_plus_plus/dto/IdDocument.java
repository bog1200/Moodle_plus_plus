package app.romail.moodle_plus_plus.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class IdDocument {
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private Account account;
	private IdDocumentType type;
	private String number;
	private String issueDate;
	private String expirationDate;
	private String personalNumber;
	private String issuingCountry;

}
