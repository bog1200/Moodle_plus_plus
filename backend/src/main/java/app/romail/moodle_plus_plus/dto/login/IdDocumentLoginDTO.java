package app.romail.moodle_plus_plus.dto.login;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class IdDocumentLoginDTO {
    private String country;
    private String pin;
}
