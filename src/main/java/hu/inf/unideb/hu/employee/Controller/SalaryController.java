package hu.inf.unideb.hu.employee.Controller;

import hu.inf.unideb.hu.employee.Controller.DTO.SalaryDTO;
import hu.inf.unideb.hu.employee.Controller.DTO.Special.UpdateSalaryRequestDTO;
import hu.inf.unideb.hu.employee.Exception.DuplicateSalaryException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Exception.UnknownSalaryException;
import hu.inf.unideb.hu.employee.Model.Salary;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.SalaryKey;
import hu.inf.unideb.hu.employee.Service.Interfaces.SalaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/salary")
public class SalaryController {

    private final SalaryService salaryService;

    @GetMapping("/allEmployees")
    public Collection<SalaryDTO> readAllEmployees() {
        return salaryService.readAllSalaries().stream()
                .map(this::convertSalaryToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{empNo}")
    public Collection<SalaryDTO> readSalariesByEmpNo(@RequestParam("empNo") int empNo) {
        try {
            return salaryService.getSalariesByEmployee(empNo).stream()
                    .map(this::convertSalaryToDTO)
                    .collect(Collectors.toList());
        } catch (UnknownEmployeeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(path = "/")
    public void createSalary(SalaryDTO salaryDTO) {
        try {
            salaryService.createSalary(convertDTOToSalary(salaryDTO));
        } catch (DuplicateSalaryException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @DeleteMapping(path = "/empNo/{empNo}/fromDate/{fromDate}")
    public void deleteSalary(@RequestParam int empNo, @RequestParam Date fromDate) {
        try {
            salaryService.deleteSalary(
                    SalaryKey.builder()
                            .empNo(empNo)
                            .fromDate(fromDate)
                            .build()
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/")
    public void updateSalary(@Valid @RequestBody UpdateSalaryRequestDTO updateSalaryRequestDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().forEach(objectError -> log.error(objectError.toString()));
        }
        try {
            salaryService.updateSalary(
                    Salary.builder()
                    .salaryKey(
                            SalaryKey.builder()
                                    .empNo(updateSalaryRequestDTO.getOldSalaryKey().getEmpNo())
                                    .fromDate(updateSalaryRequestDTO.getOldSalaryKey().getFromDate())
                                    .build()
                    )
                    .salary(updateSalaryRequestDTO.getOldSalary())
                    .toDate(updateSalaryRequestDTO.getOldToDate())
                    .build(),

                    Salary.builder()
                    .salaryKey(
                            SalaryKey.builder()
                                    .empNo(updateSalaryRequestDTO.getNewSalaryKey().getEmpNo())
                                    .fromDate(updateSalaryRequestDTO.getNewSalaryKey().getFromDate())
                                    .build()
                    )
                    .salary(updateSalaryRequestDTO.getNewSalary())
                    .toDate(updateSalaryRequestDTO.getNewToDate())
                    .build()
            );
        }catch (UnknownSalaryException | DuplicateSalaryException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    private Salary convertDTOToSalary(SalaryDTO salaryDTO) {
        return Salary.builder()
                .salaryKey(salaryDTO.getSalaryKey())
                .salary(salaryDTO.getSalary())
                .toDate(salaryDTO.getToDate())
                .build();
    }

    private SalaryDTO convertSalaryToDTO(Salary salary) {
        return SalaryDTO.builder()
                .salaryKey(salary.getSalaryKey())
                .salary(salary.getSalary())
                .toDate(salary.getToDate())
                .build();
    }

}
