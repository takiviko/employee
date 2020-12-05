package hu.inf.unideb.hu.employee.Controller;

import hu.inf.unideb.hu.employee.Controller.DTO.DeptEmpDTO;
import hu.inf.unideb.hu.employee.Exception.DuplicateDeptEmpException;
import hu.inf.unideb.hu.employee.Model.DeptEmp;
import hu.inf.unideb.hu.employee.Service.Interfaces.DeptEmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/DeptEmp")
public class DeptEmpController {

    private final DeptEmpService deptEmpService;

    @GetMapping("/DeptEmp")
    public Collection<DeptEmpDTO> listDeptEmps() {
        return deptEmpService.getAllDeptEmps().stream()
                .map(this::convertDeptEmpToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public void createDeptEmp(DeptEmpDTO deptEmpDTO) {
        try {
            deptEmpService.addDeptEmp(convertDTOToDeptEmp(deptEmpDTO));
        } catch (DuplicateDeptEmpException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    private DeptEmpDTO convertDeptEmpToDTO(DeptEmp deptEmp) {
        return DeptEmpDTO.builder()
                .deptNo(deptEmp.getDeptNo())
                .empNo((deptEmp.getEmpNo()))
                .toDate(deptEmp.getToDate())
                .fromDate(deptEmp.getFromDate())
                .build();

    }

    private DeptEmp convertDTOToDeptEmp(DeptEmpDTO deptEmpDTO) {
        return DeptEmp.builder()
                .deptNo(deptEmpDTO.getDeptNo())
                .empNo((deptEmpDTO.getEmpNo()))
                .toDate(deptEmpDTO.getToDate())
                .fromDate(deptEmpDTO.getFromDate())
                .build();

    }
}
