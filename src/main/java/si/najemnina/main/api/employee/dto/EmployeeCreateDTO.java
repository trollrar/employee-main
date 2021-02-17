package si.najemnina.main.api.employee.dto;

import si.najemnina.main.api.employee.Employee;

import java.util.Date;

public class EmployeeCreateDTO {
        public String firstName;

        public String lastName;

        public Long supervisorId;

        public Employee.Position position;
}
