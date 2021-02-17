package si.najemnina.main.api.employee;
import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(nullable = false)
    public String firstName;

    @Column(nullable = false)
    public String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    public Employee supervisor;

    @OneToMany(mappedBy = "employee")
    private Collection<Employee> supervisingEmployees;

    public Date creationDate;

    @Column(nullable = false)
    public Position position;

    public enum Position {
        OTHER, OFFICE, CUSTOMER_SERVICE, MANAGEMENT
    }
}
