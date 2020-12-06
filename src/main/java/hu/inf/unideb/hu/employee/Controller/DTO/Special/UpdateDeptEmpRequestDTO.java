package hu.inf.unideb.hu.employee.Controller.DTO.Special;

import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptEmpKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDeptEmpRequestDTO {

    DeptEmpKey oldDeptEmpKey;
    Date oldFromDate;
    Date oldToDate;

    DeptEmpKey newDeptEmpKey;
    Date newFromDate;
    Date newToDate;
}
