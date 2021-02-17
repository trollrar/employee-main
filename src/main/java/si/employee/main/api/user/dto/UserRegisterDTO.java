package si.employee.main.api.user.dto;

import si.employee.main.validators.email.ValidEmail;

public class UserRegisterDTO {
    public String username;

    @ValidEmail
    public String email;

    public String password;
}
