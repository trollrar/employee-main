package si.najemnina.main.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity // TODO: implement security: https://www.baeldung.com/registration-with-spring-mvc-and-spring-security
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    public String username;

    public String email;

    public String password;

    public Role role;

    public enum Role implements GrantedAuthority{
        USER, ADMIN;

        @Override
        public String getAuthority() {
            return name();
        }
    }

    public User() {
        role = Role.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<GrantedAuthority>(Collections.singleton(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
