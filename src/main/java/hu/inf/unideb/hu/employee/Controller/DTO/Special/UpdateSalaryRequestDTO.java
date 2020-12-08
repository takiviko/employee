package hu.inf.unideb.hu.employee.Controller.DTO.Special;

import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.SalaryKey;
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

    SalaryKey oldSalaryKey;
    int oldSalary;
    Date oldToDate;

    SalaryKey newSalaryKey;
    int newSalary;
    Date newToDate;
}
