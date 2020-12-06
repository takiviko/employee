package hu.inf.unideb.hu.employee.Controller;

import hu.inf.unideb.hu.employee.Controller.DTO.DeptEmpDTO;
import hu.inf.unideb.hu.employee.Exception.DuplicateDeptEmpException;
import hu.inf.unideb.hu.employee.Exception.UnknownDeptEmpException;
import hu.inf.unideb.hu.employee.Model.DeptEmp;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptEmpKey;
import hu.inf.unideb.hu.employee.Service.Interfaces.DeptEmpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
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

    @DeleteMapping("/")
    public void deleteDeptEmp(DeptEmpKey deptEmpKey) {
        try {
            deptEmpService.deleteDeptEmp(deptEmpKey);
        } catch (UnknownDeptEmpException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @GetMapping("/deptNo/{deptNo}/empNo/{empNo}")
    public DeptEmpDTO getDeptEmpByDeptEmpKey(@PathVariable("deptNo") String deptNo, @PathVariable("empNo") int empNo) {
        try {
            return convertDeptEmpToDTO(deptEmpService.getDeptEmp(
                    DeptEmpKey.builder()
                            .empNo(empNo)
                            .deptNo(deptNo)
                            .build()));
        } catch (UnknownDeptEmpException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public void updateDeptEmp(DeptEmpDTO oldDeptEmpDTO, DeptEmpDTO newDeptEmpDTO){

    }

    private DeptEmpDTO convertDeptEmpToDTO(DeptEmp deptEmp) {
        return DeptEmpDTO.builder()
                .deptEmpKey(deptEmp.getDeptEmpKey())
                .toDate(deptEmp.getToDate())
                .fromDate(deptEmp.getFromDate())
                .build();

    }

    private DeptEmp convertDTOToDeptEmp(DeptEmpDTO deptEmpDTO) {
        return DeptEmp.builder()
                .deptEmpKey(deptEmpDTO.getDeptEmpKey())
                .toDate(deptEmpDTO.getToDate())
                .fromDate(deptEmpDTO.getFromDate())
                .build();

    }
}
