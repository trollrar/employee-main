package si.employee.main.api.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import si.employee.main.api.employee.dto.EmployeeCreateDTO;
import si.employee.main.api.employee.dto.EmployeeDTO;
import si.employee.main.api.employee.dto.EmployeeSupervisorSetDTO;
import si.employee.main.api.employee.dto.EmployeeUpdateDTO;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping("")
    public ResponseEntity<List<EmployeeDTO>> getEmployees(HttpServletRequest request) {
        return ResponseEntity.ok(employeeRepository.findAll().stream().map(employee -> employeeMapper.toDTO(employee)).collect(Collectors.toList()));
    }

    @GetMapping("supervised/{id}")
    public ResponseEntity<List<EmployeeDTO>> getSupervisedEmployees(HttpServletRequest request, @PathVariable("id") Long id) {
        Employee supervisor = employeeRepository.findById(id).get();
        return ResponseEntity.ok(employeeRepository.findAllBySupervisor(supervisor).stream().map(employee -> employeeMapper.toDTO(employee)).collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    @Transactional
    public ResponseEntity<EmployeeDTO> getEmployee(HttpServletRequest request, @PathVariable("id") Long id) {
        Employee supervisor = employeeRepository.findById(id).get();
        employeeRepository.findAllBySupervisor(supervisor).stream().forEach(employee -> {
            employee.supervisor = null;
            employeeRepository.save(employee);
        });
        return ResponseEntity.ok(employeeMapper.toDTO(employeeRepository.findById(id).get()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public void deleteEmployee(HttpServletRequest request, @PathVariable("id") Long id) {
        employeeRepository.deleteById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("create")
    public ResponseEntity<EmployeeDTO> create(@RequestBody EmployeeCreateDTO dto) {
        Employee employee = new Employee();
        employeeMapper.update(employee, dto);
        employee.creationDate = new Date(System.currentTimeMillis());

        if (dto.supervisorId == null) {
            employee.supervisor = null;
        } else {
            Employee supervisor = employeeRepository.findById(dto.supervisorId).get();
            employee.supervisor = supervisor;
        }

        employeeRepository.save(employee);
        return ResponseEntity.ok(employeeMapper.toDTO(employee));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("update")
    public ResponseEntity<EmployeeDTO> update(@RequestBody EmployeeUpdateDTO dto) {
        Optional<Employee> optional = employeeRepository.findById(dto.id);
        //TODO: handle bad requests
        Employee employee = optional.get();

        employeeMapper.update(employee, dto);
        if (!(dto.supervisorId == null || dto.supervisorId.equals(employee.id))) {
            Employee supervisor = employeeRepository.findById(dto.supervisorId).get();
            if (supervisor.supervisor.id != dto.id) {
                employee.supervisor = supervisor;
            }
        }

        employeeRepository.save(employee);
        return ResponseEntity.ok(employeeMapper.toDTO(employee));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("supervisor")
    public ResponseEntity<EmployeeDTO> setSupervisor(@RequestBody EmployeeSupervisorSetDTO dto) {
        Optional<Employee> optional = employeeRepository.findById(dto.id);
        //TODO: handle bad requests
        Employee employee = optional.get();

        employeeMapper.update(employee, dto);

        if (!(dto.supervisorId == null || dto.supervisorId.equals(employee.id))) {
            Employee supervisor = employeeRepository.findById(dto.supervisorId).get();
            if (supervisor.supervisor.id != dto.id) {
                employee.supervisor = supervisor;
            }
        }

        employeeRepository.save(employee);
        return ResponseEntity.ok(employeeMapper.toDTO(employee));
    }
}
