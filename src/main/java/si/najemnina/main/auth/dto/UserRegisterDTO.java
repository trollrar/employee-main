package si.najemnina.main.auth.dto;

import si.najemnina.main.validators.email.ValidEmail;

public class UserRegisterDTO {
    public String username;

    @ValidEmail
    public String email;

    public String password;
}
