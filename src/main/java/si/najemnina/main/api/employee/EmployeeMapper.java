package si.najemnina.main.api.employee;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import si.najemnina.main.api.employee.dto.EmployeeCreateDTO;
import si.najemnina.main.api.employee.dto.EmployeeDTO;
import si.najemnina.main.api.employee.dto.EmployeeSupervisorSetDTO;
import si.najemnina.main.api.employee.dto.EmployeeUpdateDTO;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDTO toDTO(Employee employee);

    @Mapping(target = "supervisorId", ignore = true)
    void update(@MappingTarget Employee employee, EmployeeUpdateDTO dto);
    @Mapping(target = "supervisorId", ignore = true)
    void update(@MappingTarget Employee employee, EmployeeCreateDTO dto);
    @Mapping(target = "supervisorId", ignore = true)
    void update(@MappingTarget Employee employee, EmployeeSupervisorSetDTO dto);

}
