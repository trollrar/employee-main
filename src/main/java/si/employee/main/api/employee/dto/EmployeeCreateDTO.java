package si.employee.main.api.employee.dto;

import si.employee.main.api.employee.Employee;

public class EmployeeCreateDTO {
        public String firstName;

        public String lastName;

        public Long supervisorId;

        public Employee.Position position;
}
