package si.najemnina.main.api.employee.dto;

import si.najemnina.main.api.employee.Employee;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

public class EmployeeDTO {
    public Long id;

    public String firstName;

    public String lastName;

    public EmployeeDTO supervisor;

    public Date creationDate;

    public Employee.Position position;
}
