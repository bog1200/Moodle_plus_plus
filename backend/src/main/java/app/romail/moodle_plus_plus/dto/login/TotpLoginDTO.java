package app.romail.moodle_plus_plus.dto.login;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class TotpLoginDTO {
    private String username;
    private String totp;
}
