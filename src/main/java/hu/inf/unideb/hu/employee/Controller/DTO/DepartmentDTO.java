package hu.inf.unideb.hu.employee.Controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private String deptNo;

    private String deptName;
}
