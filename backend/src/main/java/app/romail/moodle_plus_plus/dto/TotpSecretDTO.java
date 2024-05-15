package app.romail.moodle_plus_plus.dto;

public class TotpSecretDTO {
    private String secret;

    public TotpSecretDTO(String secret) {
        this.secret = secret;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
