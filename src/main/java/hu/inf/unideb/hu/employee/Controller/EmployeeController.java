package hu.inf.unideb.hu.employee.Controller;

import hu.inf.unideb.hu.employee.Controller.DTO.EmployeeDTO;
import hu.inf.unideb.hu.employee.Controller.DTO.Special.UpdateEmployeeRequestDTO;
import hu.inf.unideb.hu.employee.Exception.DuplicateEmployeeException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Model.Employee;
import hu.inf.unideb.hu.employee.Service.Interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(path = "/allEmployees")
    public Collection<EmployeeDTO> readEmployees() {
        return employeeService.getAllEmployees().stream()
                .map(this::convertEmployeeToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/")
    public void createEmployee(EmployeeDTO employeeDTO) {
        try {
            employeeService.createEmployee(convertDTOToEmployee(employeeDTO));
        } catch (DuplicateEmployeeException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @GetMapping(path = "/empNo/{empNo}")
    public EmployeeDTO readEmployeeByEmpNo(@RequestParam int empNo) {
        try {
            return convertEmployeeToDTO(employeeService.getEmployeeById(empNo));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(path = "/gender/{gender}")
    public List<EmployeeDTO> readEmployeesByGender(@RequestParam("gender") String gender) throws UnknownEmployeeException {
        log.info("Querying gender " + gender);
        return employeeService.getEmployeesByGender(gender).stream()
                .map(this::convertEmployeeToDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping(path="/")
    public void deleteEmployee(@RequestParam int empNo) {
        try {
            employeeService.deleteEmployee(empNo);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/")
    public void updateEmployee(@Valid @RequestBody UpdateEmployeeRequestDTO updateEmployeeRequestDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().forEach(objectError -> log.error(objectError.toString()));
        }
        try {
            employeeService.updateEmployee(
                    Employee.builder()
                        .empNo(updateEmployeeRequestDTO.getOldEmpNo())
                        .birthDate(updateEmployeeRequestDTO.getOldBirthDate())
                        .firstName(updateEmployeeRequestDTO.getOldFirstName())
                        .lastName(updateEmployeeRequestDTO.getOldLastName())
                        .gender(updateEmployeeRequestDTO.getOldGender())
                        .hireDate(updateEmployeeRequestDTO.getOldHireDate())
                        .build(),

                    Employee.builder()
                        .empNo(updateEmployeeRequestDTO.getNewEmpNo())
                        .birthDate(updateEmployeeRequestDTO.getNewBirthDate())
                        .firstName(updateEmployeeRequestDTO.getNewFirstName())
                        .lastName(updateEmployeeRequestDTO.getNewLastName())
                        .gender(updateEmployeeRequestDTO.getNewGender())
                        .hireDate(updateEmployeeRequestDTO.getNewHireDate())
                        .build()
            );
        } catch (UnknownEmployeeException | DuplicateEmployeeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private EmployeeDTO convertEmployeeToDTO(Employee employee) {
        return EmployeeDTO.builder()
                .empNo(employee.getEmpNo())
                .birthDate(employee.getBirthDate())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .gender(employee.getGender())
                .hireDate(employee.getHireDate())
                .build();
    }

    private Employee convertDTOToEmployee(EmployeeDTO employeeDTO) {
        return Employee.builder()
                .empNo(employeeDTO.getEmpNo())
                .birthDate(employeeDTO.getBirthDate())
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .gender(employeeDTO.getGender())
                .hireDate(employeeDTO.getHireDate())
                .build();
    }

}
