package si.najemnina.main.api.realestate.realestate_unit.rent;

import org.hibernate.annotations.Columns;
import org.joda.money.BigMoney;
import si.najemnina.main.api.realestate.realestate_unit.RealEstateUnit;
import si.najemnina.main.api.user.User;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(nullable = false)
    private Instant creationDate;

    @Column(nullable = false)
    private Instant serviceDate;

    @Column(nullable = false)
    private Instant paymentDate;

    public String description;

    private Instant paidOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private User tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private RealEstateUnit unit;

    @Columns(columns = {
            @Column(name = "amount", precision = 10, scale = 5, nullable = false),
            @Column(name = "currency", nullable = false, length = 3)
    })
    public BigMoney amount;

    @Column(nullable = false)
    private Status status;

    public  enum Status {
        WAITING, SENT, RECIEVED, PAID
    }

}
