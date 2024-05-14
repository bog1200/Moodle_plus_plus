package app.romail.moodle_plus_plus.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@lombok.Getter
@lombok.Setter
@AllArgsConstructor
@NoArgsConstructor
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

	public IdDocument(Account account, IdDocumentType type, String number, String issueDate, String expirationDate, String personalNumber, String issuingCountry) {
		this.account = account;
		this.type = type;
		this.number = number;
		this.issueDate = issueDate;
		this.expirationDate = expirationDate;
		this.personalNumber = personalNumber;
		this.issuingCountry = issuingCountry;
	}

}
