package hu.inf.unideb.hu.employee.Controller;

import hu.inf.unideb.hu.employee.Controller.DTO.DepartmentDTO;
import hu.inf.unideb.hu.employee.Controller.DTO.Special.UpdateDepartmentRequestDTO;
import hu.inf.unideb.hu.employee.Exception.DuplicateDepartmentException;
import hu.inf.unideb.hu.employee.Exception.UnknownDepartmentException;
import hu.inf.unideb.hu.employee.Model.Department;
import hu.inf.unideb.hu.employee.Service.Interfaces.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping(path = "/allDepartments")
    public Collection<DepartmentDTO> listDepartments() {
        return departmentService.getAllDepartments().stream()
                .map(this::convertDepartmentToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{deptName}")
    public DepartmentDTO getDepartmentByName(@PathVariable String deptName) {
        try {
            return convertDepartmentToDTO(departmentService.getDepartmentByDeptName(deptName));
        } catch (UnknownDepartmentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/")
    public void deleteDepartment(@RequestParam String deptNo) {
        try {
            departmentService.deleteDepartment(deptNo);
        } catch (UnknownDepartmentException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @PutMapping("/")
    public void updateDepartment(@Valid @RequestBody UpdateDepartmentRequestDTO updateDepartmentRequestDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().forEach(objectError -> log.error(objectError.toString()));
        }
        try {
            departmentService.updateDepartment(
                    Department.builder()
                    .deptName(updateDepartmentRequestDTO.getOldDeptName())
                    .deptNo(updateDepartmentRequestDTO.getOldDeptNo())
                    .build(),

                    Department.builder()
                    .deptName(updateDepartmentRequestDTO.getNewDeptName())
                    .deptNo(updateDepartmentRequestDTO.getNewDeptNo())
                    .build()
            );
        } catch (UnknownDepartmentException | DuplicateDepartmentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/")
    public void createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        try {
            departmentService.createDepartment(convertDTOToDepartment(departmentDTO));
        } catch (DuplicateDepartmentException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    private DepartmentDTO convertDepartmentToDTO(Department department) {
        return DepartmentDTO.builder()
                .deptName(department.getDeptName())
                .deptNo(department.getDeptNo())
                .build();
    }

    private Department convertDTOToDepartment(DepartmentDTO departmentDTO) {
        return Department.builder()
                .deptName(departmentDTO.getDeptName())
                .deptNo(departmentDTO.getDeptNo())
                .build();
    }

}
