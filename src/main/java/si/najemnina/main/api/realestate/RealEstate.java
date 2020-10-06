package si.najemnina.main.api.realestate;

import si.najemnina.main.api.realestate.realestate_unit.RealEstateUnit;
import si.najemnina.main.api.user.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "real_estate")
public class RealEstate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    public String name;

    public String description;

    public String contentDescription;

    public Type type;

    @OneToMany(mappedBy = "real_estate", fetch = FetchType.EAGER)
    public Collection<RealEstateUnit> units;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    public User owner;

    public enum Type {
        RESIDENTIAL, LAND, COMERCIAL, INDUSTRIAL
    }
}
