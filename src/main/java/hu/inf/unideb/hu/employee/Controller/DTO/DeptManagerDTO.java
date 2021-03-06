package hu.inf.unideb.hu.employee.Controller.DTO;

import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptEmpKey;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptManagerKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DeptManagerDTO {
    DeptManagerKey deptManagerKey;
    Date fromDate;
    Date toDate;
}
