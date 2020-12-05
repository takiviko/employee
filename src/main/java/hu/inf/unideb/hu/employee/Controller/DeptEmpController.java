package hu.inf.unideb.hu.employee.Controller;

import hu.inf.unideb.hu.employee.Controller.DTO.DeptEmpDTO;
import hu.inf.unideb.hu.employee.Model.DeptEmp;
import hu.inf.unideb.hu.employee.Service.Interfaces.DeptEmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



    private DeptEmpDTO convertDeptEmpToDTO(DeptEmp deptEmp) {
        return DeptEmpDTO.builder()
                .dept_no(deptEmp.getDept_no())
                .emp_no((deptEmp.getEmp_no()))
                .to_date(deptEmp.getTo_date())
                .from_date(deptEmp.getFrom_date())
                .build();

    }
}
