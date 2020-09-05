package si.najemnina.main.api.user.dto;

import org.springframework.security.core.GrantedAuthority;
import si.najemnina.main.api.user.User;

import java.util.Collection;

public class UserDTO {
    public long id;

    public String username;

    public User.Role role;

    public Collection<? extends GrantedAuthority> authorities;

    public String email;
}
