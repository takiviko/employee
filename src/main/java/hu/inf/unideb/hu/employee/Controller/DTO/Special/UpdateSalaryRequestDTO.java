package hu.inf.unideb.hu.employee.Controller.DTO.Special;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSalaryRequestDTO {

    int oldEmpNo;
    int oldSalary;
    Date oldFromDate;
    Date oldToDate;

    int newEmpNo;
    int newSalary;
    Date newFromDate;
    Date newToDate;
}
