package si.najemnina.main.api.employee;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(nullable = false)
    public String firstName;

    @Column(nullable = false)
    public String lastName;

    public Employee employee;

    public Date creationDate;

    public Position position;

    public enum Position {
        JUNIOR, MIDDLE, SENIOR, DIRECTOR
    }
}
