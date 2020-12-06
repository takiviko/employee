package hu.inf.unideb.hu.employee.Controller.DTO.Special;

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
public class UpdateDeptManagerRequestDTO {

    DeptManagerKey oldDeptManagerKey;
    Date oldFromDate;
    Date oldToDate;


    DeptManagerKey newDeptManagerKey;
    Date newFromDate;
    Date newToDate;
}
