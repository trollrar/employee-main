package si.employee.main.api.employee;

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

    @ManyToOne(fetch = FetchType.EAGER)
    public Employee supervisor;

    @OneToMany(mappedBy = "supervisor", fetch = FetchType.LAZY)
    public Collection<Employee> employees;

    @Column(nullable = false)
    public Date creationDate;

    @Column(nullable = false)
    public Position position;

    public enum Position {
        OTHER, OFFICE, CUSTOMER_SERVICE, MANAGEMENT
    }
}
