package hu.inf.unideb.hu.employee.Controller.DTO;

import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptEmpKey;
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
    DeptEmpKey deptEmpKey;
    Date fromDate;
    Date toDate;
}
