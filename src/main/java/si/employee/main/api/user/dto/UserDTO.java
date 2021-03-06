package si.employee.main.api.user.dto;

import org.springframework.security.core.GrantedAuthority;
import si.employee.main.api.user.User;

import java.util.Collection;

public class UserDTO {
    public Long id;

    public String username;

    public User.Role role;

    public Collection<? extends GrantedAuthority> authorities;

    public String email;
}
