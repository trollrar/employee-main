package si.najemnina.main.api.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import si.najemnina.main.api.realestate.RealEstate;
import si.najemnina.main.api.realestate.realestate_unit.RealEstateUnit;
import si.najemnina.main.api.realestate.realestate_unit.rent.Rent;

import javax.persistence.*;
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "real_estate_units_users",
            joinColumns = @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "real_estate_unit_id", nullable = false, referencedColumnName = "id"))
    public Collection<RealEstateUnit> usedRealEstateUnits;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public Collection<RealEstate> ownedRealEstates;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public Collection<RealEstateUnit> rentedRealEstateUnits;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public Collection<Rent> rents;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public Collection<Rent> rentsOwned;

    public enum Role implements GrantedAuthority {
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
