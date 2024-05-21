
package app.romail.moodle_plus_plus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class IdDocumentDTO {
private Long id;
    private Long account_id;
    private String type;
    private String number;
    private String issueDate;
    private String expirationDate;
    private String personalNumber;
    private String issuingCountry;
}
