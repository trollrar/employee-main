package si.najemnina.main.api.employee;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import si.najemnina.main.api.employee.dto.EmployeeCreateDTO;
import si.najemnina.main.api.employee.dto.EmployeeDTO;
import si.najemnina.main.api.employee.dto.EmployeeSupervisorSetDTO;
import si.najemnina.main.api.employee.dto.EmployeeUpdateDTO;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDTO toDTO(Employee employee);

    void update(@MappingTarget Employee employee, EmployeeUpdateDTO dto);

    void update(@MappingTarget Employee employee, EmployeeCreateDTO dto);

    void update(@MappingTarget Employee employee, EmployeeSupervisorSetDTO dto);

}
