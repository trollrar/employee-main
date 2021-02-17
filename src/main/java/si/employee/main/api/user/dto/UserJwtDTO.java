package si.employee.main.api.user.dto;

public class UserJwtDTO {
    public String token;

    public UserDTO userDTO;

    public UserJwtDTO(String token, UserDTO userDTO) {
        this.token = token;
        this.userDTO = userDTO;
    }
}
