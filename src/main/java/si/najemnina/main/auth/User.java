package si.najemnina.main.auth;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // TODO: implement security: https://www.baeldung.com/registration-with-spring-mvc-and-spring-security
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    public String name;

    public String email;

    public String password;

    public Role role;

    public enum Role {
        USER, ADMIN
    }

    User() {
        role = Role.USER;
    }
}
