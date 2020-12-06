package hu.inf.unideb.hu.employee.Controller;

import hu.inf.unideb.hu.employee.Controller.DTO.DeptManagerDTO;
import hu.inf.unideb.hu.employee.Controller.DTO.Special.UpdateDeptManagerRequestDTO;
import hu.inf.unideb.hu.employee.Exception.*;
import hu.inf.unideb.hu.employee.Model.DeptManager;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptManagerKey;
import hu.inf.unideb.hu.employee.Service.Interfaces.DeptManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/DeptManager")
@Slf4j
@RequiredArgsConstructor
public class DeptManagerController {

    private final DeptManagerService deptManagerService;

    @GetMapping("/DeptManagers")
    public Collection<DeptManagerDTO> listDeptManagers() {
        return deptManagerService.getAllManagers().stream()
                .map(this::convertDeptManagerToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public void createDeptManager(DeptManagerDTO deptManagerDTO) {
        try {
            deptManagerService.addDeptManager(convertDTOToDeptManager(deptManagerDTO));
        } catch (DuplicateDeptManagerException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @DeleteMapping("/")
    public void deleteDeptManager(DeptManagerKey deptManagerKey) {
        try {
            deptManagerService.deleteDeptManager(deptManagerKey);
        } catch (UnknownDeptManagerException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @GetMapping("/deptNo/{deptNo}/empNo/{empNo}")
    public DeptManagerDTO getDeptEmpByDeptManagerKey(@PathVariable("deptNo") String deptNo, @PathVariable("empNo") int empNo) {
        try {
            return convertDeptManagerToDTO(deptManagerService.getDeptManagerByKey(
                    DeptManagerKey.builder()
                            .empNo(empNo)
                            .deptNo(deptNo)
                            .build()
            ));
        } catch (UnknownDeptManagerException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/")
    public void updateDeptManager(@Valid @RequestBody UpdateDeptManagerRequestDTO updateDeptManagerRequestDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().forEach(objectError -> log.error(objectError.toString()));
        }
        try {
            deptManagerService.updateDeptManager(
                    DeptManager.builder()
                            .deptManagerKey(updateDeptManagerRequestDTO.getOldDeptManagerKey())
                            .fromDate(updateDeptManagerRequestDTO.getOldFromDate())
                            .toDate(updateDeptManagerRequestDTO.getOldToDate())
                            .build(),

                    DeptManager.builder()
                            .deptManagerKey(updateDeptManagerRequestDTO.getNewDeptManagerKey())
                            .fromDate(updateDeptManagerRequestDTO.getNewFromDate())
                            .toDate(updateDeptManagerRequestDTO.getNewToDate())
                            .build()
            );
        } catch (UnknownDeptManagerException | DuplicateDeptManagerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private DeptManagerDTO convertDeptManagerToDTO(DeptManager deptManager) {
        return DeptManagerDTO.builder()
                .deptManagerKey(deptManager.getDeptManagerKey())
                .toDate(deptManager.getToDate())
                .fromDate(deptManager.getFromDate())
                .build();

    }

    private DeptManager convertDTOToDeptManager(DeptManagerDTO deptManagerDTO) {
        return DeptManager.builder()
                .deptManagerKey(deptManagerDTO.getDeptManagerKey())
                .toDate(deptManagerDTO.getToDate())
                .fromDate(deptManagerDTO.getFromDate())
                .build();

    }

}
