package hu.inf.unideb.hu.employee.Controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeptEmpDTO {
    int empNo;
    String deptNo;
    Date fromDate;
    Date toDate;
}
