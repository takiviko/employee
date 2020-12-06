package hu.inf.unideb.hu.employee.Model;

import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptEmpKey;
import lombok.*;

import java.util.Date;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeptEmp {
    DeptEmpKey deptEmpKey;
    Date fromDate;
    Date toDate;
}
