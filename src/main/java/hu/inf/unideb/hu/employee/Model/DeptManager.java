package hu.inf.unideb.hu.employee.Model;

import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptEmpKey;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptManagerKey;
import lombok.*;

import java.util.Date;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeptManager {
    DeptManagerKey deptManagerKey;
    Date fromDate;
    Date toDate;
}
