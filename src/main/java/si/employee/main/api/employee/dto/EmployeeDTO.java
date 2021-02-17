package si.employee.main.api.employee.dto;

import si.employee.main.api.employee.Employee;

import java.util.Date;

public class EmployeeDTO {
    public Long id;

    public String firstName;

    public String lastName;

    public EmployeeDTO supervisor;

    public Date creationDate;

    public Employee.Position position;
}
