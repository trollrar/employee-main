package si.najemnina.main.api.employee;
import si.najemnina.main.api.user.User;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(nullable = false)
    public String firstName;

    @Column(nullable = false)
    public String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    public Employee supervisor;

    @OneToMany(mappedBy = "supervisor", fetch = FetchType.LAZY)
    private Collection<Employee> employees;

    public Date creationDate;

    @Column(nullable = false)
    public Position position;

    public enum Position {
        OTHER, OFFICE, CUSTOMER_SERVICE, MANAGEMENT
    }
}
