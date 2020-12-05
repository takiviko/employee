package hu.inf.unideb.hu.employee.Controller.DTO.Special;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDepartmentRequestDTO {

    private String oldDeptNo;
    private String oldDeptName;

    private String newDeptNo;
    private String newDeptName;
}
