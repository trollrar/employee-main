package si.najemnina.main.api.realestate.realestate_unit;

import org.hibernate.annotations.Columns;
import org.joda.money.BigMoney;
import si.najemnina.main.api.realestate.RealEstate;
import si.najemnina.main.api.user.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "real_estate_unit")
public class RealEstateUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    public String name;

    public String description;

    public String contentDescription;

    public Type type;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "real_estate_units_users",
            joinColumns = @JoinColumn(name = "real_estate_unit_id", nullable = false, referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id"))
    public Collection<User> users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "real_estate_id")
    public RealEstate realEstate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    public User tenant;

    @Columns(columns = {
            @Column(name = "rent", precision = 10, scale = 5, nullable = false),
            @Column(name = "rent_currency", nullable = false, length = 3)
    })
    public BigMoney rent;

    public enum Type {
        APARTMENT, ROOM, OTHER
    }
}
